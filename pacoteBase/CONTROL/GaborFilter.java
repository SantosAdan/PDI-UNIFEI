package pacoteBase.CONTROL;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RenderedImage;

public class GaborFilter {
   private static final double[] DEFAULT_ORIENTATIONS = new double[] {0};
   private static final double DEFAULT_WAVE_LENGTH = 1;
   private static final double DEFAULT_PHASE_OFFSET = 0;
   private static final double DEFAULT_ASPECT_RATIO = 0.5;
   private static final double DEFAULT_BANDWIDTH = 1;
   private static final int DEFAULT_WIDTH = 3;
   private static final int DEFAULT_HEIGHT = 3;

   private static final double MIN_ASPECT_RATIO = 0;
   private static final double MAX_ASPECT_RATIO = 1;

   private double[] orientations;
   private double waveLength;
   private double phaseOffset;
   private double aspectRatio;
   private double bandwidth;
   private int width;
   private int height;

   public GaborFilter() {
      this(DEFAULT_WAVE_LENGTH);
   }

   public GaborFilter(double waveLength) {
      this(waveLength, DEFAULT_ORIENTATIONS);
   }

   public GaborFilter(double waveLength, double[] orientations) {
      this(waveLength, orientations, DEFAULT_PHASE_OFFSET);
   }

   public GaborFilter(double waveLength, double[] orientations, double phaseOffset) {
      this(waveLength, orientations, phaseOffset, DEFAULT_ASPECT_RATIO);
   }

   public GaborFilter(double waveLength, double[] orientations, double phaseOffset, double aspectRatio) {
      this(waveLength, orientations , phaseOffset, aspectRatio, DEFAULT_BANDWIDTH);
   }

   public GaborFilter(double waveLength, double[] orientations, double phaseOffset, double aspectRatio, double bandwidth) {
      this(waveLength, orientations, phaseOffset, aspectRatio, bandwidth, DEFAULT_WIDTH, DEFAULT_HEIGHT);
   }

   public GaborFilter(double waveLength, double[] orientations, double phaseOffset, double aspectRatio, double bandwidth, int width, int height) {
      this.waveLength = waveLength;
      this.orientations = orientations;
      this.phaseOffset = phaseOffset;
      this.aspectRatio = aspectRatio;
      this.bandwidth = bandwidth;
      this.width = width;
      this.height = height;
   }

   public double[] getOrientations() {
      return orientations;
   }

   public void setOrientations(double[] orientations) {
      this.orientations = orientations;
   }

   public double getWaveLength() {
      return waveLength;
   }

   public void setWaveLength(double waveLength) {
      if(waveLength > 0) {
         this.waveLength = waveLength;
      } else {
         System.out.println("The Wave Length should be a positive number");
      }
   }

   public double getPhaseOffset() {
      return phaseOffset;
   }

   public void setPhaseOffset(double phaseOffset) {
      this.phaseOffset = phaseOffset;
   }

   public double getAspectRatio() {
      return aspectRatio;
   }

   public void setAspectRatio(double aspectRatio) {
      if(aspectRatio <= MAX_ASPECT_RATIO && aspectRatio >= MIN_ASPECT_RATIO) {
         this.aspectRatio = aspectRatio;
      } else {
         System.out.println("The Aspect Ratio should be in the range [" + MIN_ASPECT_RATIO + "; " + MAX_ASPECT_RATIO +"]");
      }
   }

   public double getBandwidth() {
      return bandwidth;
   }

   public void setBandwidth(double bandwidth) {
      this.bandwidth = bandwidth;
   }

   private static double calculateSigma(double waveLength, double bandwidth) {
      return waveLength*Math.sqrt(Math.log(2)/2)*(Math.pow(2, bandwidth) + 1)/((Math.pow(2, bandwidth) - 1)*Math.PI);
   }


   private static double gaborFunction(double x, double y, double sigma, double aspectRatio, double waveLength, double phaseOffset) {
      double gaborReal = Math.exp(-(Math.pow(x/sigma, 2) + Math.pow(y*aspectRatio/sigma, 2))/2)*Math.cos(2*Math.PI*x/waveLength + phaseOffset);
      double gaborImage = Math.exp(-(Math.pow(x/sigma, 2) + Math.pow(y*aspectRatio/sigma, 2))/2)*Math.sin(2*Math.PI*x/waveLength + phaseOffset);
      return Math.sqrt(Math.pow(gaborReal, 2) + Math.pow(gaborImage, 2));
   }

   public ConvolveOp getConvolveOp() {
      return new ConvolveOp(getKernel(), ConvolveOp.EDGE_NO_OP, null);
   }

   public Kernel getKernel() {
      double sigma = calculateSigma(waveLength, bandwidth);
      float[] data = new float[width*height];
      for(int k = 0, x = -width/2; x <= width/2; x++) {
         for(int y = -height/2; y <= height/2; y++) {
            for(double orientation : orientations) {
               double x1 = x*Math.cos(orientation) + y*Math.sin(orientation);
               double y1 = -x*Math.sin(orientation) + y*Math.cos(orientation);
               data[k] += (float)(gaborFunction(x1, y1, sigma, aspectRatio, waveLength, phaseOffset));
            }
            k++;
         }
      }
      float sum = 0f;
      for(int i = 0; i < width; i++) {
         for(int j = 0; j < height; j++) {
            sum += data[i*j + j];
         }
      }
      sum /= width*height;
      for(int i = 0; i < width; i++) {
         for(int j = 0; j < height; j++) {
            data[i*j + j] -= sum;
         }
      }
      return new Kernel(width, height, data);
   }

   public int getWidth() {
      return width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public RenderedImage filter(BufferedImage bufferedImage, BufferedImage bufferedImageDestination) {
      return getConvolveOp().filter(bufferedImage, bufferedImageDestination);
   }
}
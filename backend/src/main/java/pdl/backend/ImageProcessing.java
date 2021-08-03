package pdl.backend;

import net.imglib2.RandomAccess;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.loops.LoopBuilder;
import io.scif.SCIFIO;
import io.scif.img.ImgIOException;
import io.scif.img.ImgOpener;
import io.scif.img.ImgSaver;
import io.scif.img.SCIFIOImgPlus;
import net.imglib2.Cursor;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;
import net.imglib2.exception.IncompatibleTypeException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ImageProcessing {
	public static void borders(Img<UnsignedByteType> input, Img<UnsignedByteType> output) {
		final RandomAccess<UnsignedByteType> in = input.randomAccess();
		final RandomAccess<UnsignedByteType> out = output.randomAccess();
		colorToGray(input);
		int [] gx = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
		int [] gy = { 1, 2, 1, 0, 0, 0, -1, -2, -1};

		final int iw = (int) input.max(0);
		final int ih = (int) input.max(1);

		for (int i = 0; i<iw-2; i++){
			for (int j = 0; j<ih-2; j++){
				int val_v = 0;
				int val_h = 0;
				for (int x = 0; x < 3; x++){
					for (int y = 0; y < 3; y++){
						in.setPosition(i + x, 0);
						in.setPosition(j + y, 1);
						int t = x*3 + y;
						val_v = val_v + gx[t] * in.get().get();
						val_h = val_h + gy[t] * in.get().get();						
					}
				}
				out.setPosition(i, 0);
				out.setPosition(j, 1);
				int res = (int)Math.sqrt(Math.pow(val_v, 2) + Math.pow(val_h, 2));
				out.get().set(res);
			}
		}
	}

	public static void pixelisation(Img<UnsignedByteType> input, int delta){
		final RandomAccess<UnsignedByteType> in = input.randomAccess();
		final int iw = (int) input.max(0);
		final int ih = (int) input.max(1);

		int center = delta/2;

		for (int w = 0; w < (iw-delta-1); w=w+delta){
			for (int h = 0; h < (ih-delta-1); h=h+delta){
				int [] tab = {0, 0, 0};
				in.setPosition(w+center, 0);
				in.setPosition(h+center, 1);
				for (int ch = 0; ch<3; ch++){
					in.setPosition(ch, 2);
					tab[ch] = in.get().get();
				}

				for(int x = 0; x<delta; x++){
					for (int y = 0; y<delta; y++){
						in.setPosition(w+x, 0);
						in.setPosition(h+y, 1);
						for (int ch = 0; ch<3; ch++){
							in.setPosition(ch, 2);
							in.get().set(tab[ch]);
						}
					}
				}
			}
		}
	}


	public static void colorToGray(Img<UnsignedByteType> input){
        final RandomAccess<UnsignedByteType> r = input.randomAccess();
        final int iw = (int) input.max(0);
        final int ih = (int) input.max(1);

        double [] tab = {0.3, 0.59, 0.11};

        // les valeurs sur les canaux rouge et vert sont mises à 0

        for (int y = 0; y <= ih; ++y) {
            for (int x = 0; x <= iw; ++x) {
                int sum = 0;
                r.setPosition(x, 0);
                r.setPosition(y, 1);
                for (int channel = 0; channel <= 2; channel++) {
                    r.setPosition(channel, 2);
                    sum += tab[channel]*r.get().get() ;
                }
                for (int channel = 0; channel <= 2; channel++){
                    r.setPosition(channel, 2);
                    r.get().set(sum);
                }
            }
        }
    }
	
    public static void  luminosite2(Img<UnsignedByteType>  img, int delta) {
		final Cursor< UnsignedByteType > cursor = img.cursor();
		while ( cursor.hasNext() ){
		   cursor.fwd();
		final UnsignedByteType t = cursor.get();
		if(t.get()+delta > 255){
			t.set(255);
		}
		else if(t.get()+delta < 0){
			t.set(0);
		}
		else{
			t.set((t.get()+delta));
		}
		}
	}

	public static void blueFilterWithLoopBuilder(Img<UnsignedByteType> input) {
        final IntervalView<UnsignedByteType> inputR = Views.hyperSlice(input, 2, 0);
		final IntervalView<UnsignedByteType> inputG = Views.hyperSlice(input, 2, 1);
		final IntervalView<UnsignedByteType> inputB = Views.hyperSlice(input, 2, 2);

        
        LoopBuilder.setImages(inputR, inputG).forEachPixel(
            (r,g) -> { 
                r.set(0);
                g.set(0);
	
            }  
        );
    }

	public static void redFilterWithLoopBuilder(Img<UnsignedByteType> input) {
        final IntervalView<UnsignedByteType> inputR = Views.hyperSlice(input, 2, 0);
		final IntervalView<UnsignedByteType> inputG = Views.hyperSlice(input, 2, 1);
		final IntervalView<UnsignedByteType> inputB = Views.hyperSlice(input, 2, 2);

        LoopBuilder.setImages(inputB, inputG).forEachPixel(
            (b,g) -> { 
                b.set(0);
                g.set(0);
	
            }  
        );
    }

	public static void greenFilterWithLoopBuilder(Img<UnsignedByteType> input) {
        final IntervalView<UnsignedByteType> inputR = Views.hyperSlice(input, 2, 0);
		final IntervalView<UnsignedByteType> inputG = Views.hyperSlice(input, 2, 1);
		final IntervalView<UnsignedByteType> inputB = Views.hyperSlice(input, 2, 2);
        LoopBuilder.setImages(inputR, inputB).forEachPixel(
            (r,b) -> { 
                r.set(0);
                b.set(0);
            }  
        );
    }
	public static void GrayFilter (Img<UnsignedByteType> input) {
        final RandomAccess<UnsignedByteType> r = input.randomAccess();
        final int iw = (int) input.max(0);
        final int ih = (int) input.max(1);
		double [] tab={0.3,0.59,0.11};
        for (int y = 0; y <= ih; ++y) {
            for (int x = 0; x <= iw; ++x) {
				int sum = 0;
				for(int channel=0;channel<=2;channel++){
					r.setPosition(x, 0);
					r.setPosition(y, 1);
					r.setPosition(channel, 2);
					sum+=r.get().get()*tab[channel];
				}
				for(int channel=0;channel<=2;channel++){
					r.setPosition(x, 0);
					r.setPosition(y, 1);
					r.setPosition(channel, 2);
					r.get().set(sum);
				}	
            }
    	}
	}

	public static void rgbToHsv(int r, int g, int b, float[] hsv){
		double _r = r/255.0;
		double _g = g/255.0;
		double _b = b/255.0;

		double Cmax = Math.max(_r, Math.max(_g, _b));
		double Cmin = Math.min(_r, Math.min(_g, _b));
		double delta = Cmax - Cmin;

		if (Cmax == Cmin) hsv[0] = 0;
		else if (Cmax == _r) hsv[0] = (float)((60 * ((_g - _b)/delta) + 360) % 360);
		else if (Cmax == _g) hsv[0] = (float)((60 * ((_b - _r)/delta) + 120) % 360);
		else if (Cmax == _b) hsv[0] = (float)((60 * ((_r - _g)/delta) + 240) % 360);
		//Lightness of the image
		if (Cmax==0) hsv[1] = (float)(0);
		else{hsv[1] = (float) (delta/Cmax);}
		//Saturation
		hsv[2] = (float) (Cmax);
	}

	public static void hsvToRgb(float h, float s, float v, int[] rgb){
		int x = 0;
		if (h!=0) {x = (int)(h/60 % 6);};

		double C = v * s;
		double X = C*(1 - Math.abs((h/60)%2-1));
		double m = v - C;
		double _C = (C+m)*255;
		double _X = (X+m)*255;
		double _Z = m*255;

		if (x==0) {rgb[0] = (int)_C; rgb[1] = (int)_X; rgb[2] = (int)_Z;}
		else if (x==1){ rgb[0] = (int)_X; rgb[1] = (int)_C; rgb[2] = (int)_Z;}
		else if (x==2){ rgb[0] = (int)_Z; rgb[1] = (int)_C; rgb[2] = (int)_X;}
		else if (x==3){ rgb[0] = (int)_Z; rgb[1] = (int)_X; rgb[2] = (int)_C;}
		else if (x==4){ rgb[0] = (int)_X; rgb[1] = (int)_Z; rgb[2] = (int)_C;}
		else if (x==5){ rgb[0] = (int)_C; rgb[1] = (int)_Z; rgb[2] = (int)_X;}
	}
	
	
	public static void hueModification(Img<UnsignedByteType> input, long delta){
		final IntervalView<UnsignedByteType> inputR = Views.hyperSlice(input, 2, 0);
		final IntervalView<UnsignedByteType> inputG = Views.hyperSlice(input, 2, 1);
		final IntervalView<UnsignedByteType> inputB = Views.hyperSlice(input, 2, 2);

		final Cursor<UnsignedByteType> cR = inputR.cursor();
		final Cursor<UnsignedByteType> cG = inputG.cursor();
		final Cursor<UnsignedByteType> cB = inputB.cursor();
		
		while (cR.hasNext() && cG.hasNext() && cB.hasNext()) {
			cR.fwd();
			cG.fwd();
			cB.fwd();
            
			//On obtien les valeurs de rgb du pixel
            int r = cR.get().get();
			int g = cG.get().get();
			int b = cB.get().get();

			//transformation de rgb a hsv -> on modifie h par delta
			float[] hsv = new float[3];
			rgbToHsv(r, g, b, hsv);
			hsv[0] = delta;

			//transformation de hsv a rgb apres modification
			int [] rgb = new int[3];
			hsvToRgb(hsv[0], hsv[1], hsv[2], rgb);

			//modification des valeurs dans l'image
			cR.get().set(rgb[0]);
			cG.get().set(rgb[1]);
			cB.get().set(rgb[2]);
        }
	}
	
	public static void meanFilterSimple(final Img<UnsignedByteType> input, int s) {
        final RandomAccess<UnsignedByteType> in = input.randomAccess();

        final int iw = (int) input.max(0);
        final int ih = (int) input.max(1);

        int size = (int)Math.pow(2*s+1, 2);

        for (int x = s; x < iw-s; x++) {
            for (int y = s; y < ih-s; y++) {
                int [] tab = {0, 0, 0};
                for (int u = - s; u <= s; u++){
                    for (int v = -s; v<= s; v++){
                        for (int ch = 0; ch<=2; ch++){
                            in.setPosition(x + u, 0);
                            in.setPosition(y + v, 1);
                            in.setPosition(ch, 2);
                            tab[ch] += in.get().get();
                        }
                    }
                }

                for (int ch = 0; ch <=2; ch++){
                    in.setPosition(x, 0);
                    in.setPosition(y, 1);
                    in.setPosition(ch, 2);
                    in.get().set(tab[ch]/size);
                }
            }
        }
    }

	public static void contrasted2 (Img <UnsignedByteType> img, int min, int max){
		final Cursor <UnsignedByteType> cursor = img.cursor();
		while(cursor.hasNext()){
			cursor.fwd();
			final UnsignedByteType val = cursor.get();
			if((max*(val.get()-min)/(max-min))<0)
				val.set(0);
			else if ((255*(val.get()-min)/(max-min))>255)
				val.set(255);
			
			else
				val.set(max*(val.get()-min)/(max-min));
		}
	}

	public static int[] histogramme( Img <UnsignedByteType> img ){
		final RandomAccess<UnsignedByteType> r=img.randomAccess();
		final int iw = (int) img.max(0);
		final int ih = (int) img.max(1);
		int cmpt=0;
		int tab[]= new int [256];
		for(int i = 0;i<=255;i++){
			for(int x=0; x<=iw; ++x){
				for(int y=0;y<=ih;++y){
					r.setPosition(x,0);
					r.setPosition(y, 1);
					final UnsignedByteType val = r.get();
					if(val.get()== i)
					cmpt=cmpt+1;
				}
			}
			tab[i] =cmpt;
			//System.out.print(tab[i]);
			cmpt=0;
		}
		return tab;
	}
}
    


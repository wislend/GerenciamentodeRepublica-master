package com.example.deyvi.gerenciamentoderepublica.Util.validacion;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.activeandroid.Cache.getContext;

public class ImageUtil {


    public static byte[] decodeInByte(Uri file){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = decodeSampledBitmapFromFile(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }



    // Retorna um Uri com o caminho do arquivo para guardar a foto
    public static Uri getOutputPictureUri(String pasta){

        String mm = Environment.MEDIA_MOUNTED;
        String externalStorageState = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(externalStorageState)){
            return null;
        }


        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), pasta);


        // Cria a pasta se não existir
        if (!mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Toast.makeText(getContext(),pasta, Toast.LENGTH_LONG).show();
                return null;
            }
        }

        // Cria o path completo com o nome da foto
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
        return Uri.fromFile(mediaFile);
    }

    public static Bitmap decodeSampledBitmapFromFile(Uri fileUri,
                                                     int reqWidth, int reqHeight) {

        String path = fileUri.getPath();

        // Primeiro faz o decode com inJustDecodeBounds=true para obter as dimensões
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calcula as novas dimensões
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Faz o decode do bitmap e redimensiona
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path);
    }

    public static Bitmap decodeSampledBitmapFromFile(Uri fileUri) {

        String path = fileUri.getPath();

        // Primeiro faz o decode com inJustDecodeBounds=true para obter as dimensões
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Faz o decode do bitmap e redimensiona
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Altura e largura da imagem
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            //Calcula o maior valor de inSampleSize, que é uma potência de 2,
            // que mantém altura e o comprimento maiores do que os valores pedidos.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


    public static Bitmap decodeImagemBitMap(String imagem) {
        byte[] bytes = Base64.decode(imagem, Base64.NO_WRAP);
        Bitmap bitmap  = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }

    public static String decodeImageString(byte[] bit) {
        return Base64.encodeToString(bit,Base64.DEFAULT);
    }

    public  static String decodeImageString(Uri file) {
        byte[] bytes = decodeInByte(file);
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }



}

package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class libraryZxing extends AppCompatActivity {

    ImageView imageviewZ;
    Button btnZ;
    EditText edtTxtZ;
    String EditTextValue;
    Thread thread;
    public final static int QRcodeWidth = 500;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_zxing);
        getSupportActionBar().setTitle("library zxing");

        imageviewZ = findViewById(R.id.imageViewZ);
        edtTxtZ = findViewById(R.id.editTextZxing);
        btnZ = findViewById(R.id.btnZ);

        btnZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextValue = edtTxtZ.getText().toString();

                try {
                    bitmap = TextToImageEncode(EditTextValue);
                    imageviewZ.setImageBitmap(bitmap);
                }catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });

    }

    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );
        }catch (IllegalArgumentException Illegalargumentexception){
            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++){
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++){
                pixels[offset + x] = bitMatrix.get(x,y) ?
                        getResources().getColor(R.color.CodeBlackColor):
                        getResources().getColor(R.color.CodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}

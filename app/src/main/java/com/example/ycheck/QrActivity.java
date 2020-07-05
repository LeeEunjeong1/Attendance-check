package com.example.ycheck;

import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.widget.ImageView;

        import androidx.appcompat.app.AppCompatActivity;

public class QrActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        imageView = (ImageView) this.findViewById(R.id.imageView);
        Bitmap bitmap = getIntent().getParcelableExtra("pic");
        imageView.setImageBitmap(bitmap);
    }
}
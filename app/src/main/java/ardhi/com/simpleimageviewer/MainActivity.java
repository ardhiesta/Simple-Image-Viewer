package ardhi.com.simpleimageviewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {
    private static final int IO_BUFFER_SIZE = 4 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//try {
//    URL url = new URL("http://images.detik.com/content/2014/11/06/10/194814_022933_judionline.jpg");
//    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//    connection.setDoInput(true);
//    connection.connect();
//    InputStream input = connection.getInputStream();
//    Bitmap myBitmap = BitmapFactory.decodeStream(input);
//
//    ImageView imageView = (ImageView) findViewById(R.id.imageView);
//    imageView.setImageBitmap(myBitmap);
//}catch(Exception e){
//    e.printStackTrace();
//}
//        try {
//            ImageView i = (ImageView)findViewById(R.id.imageView);
////            URL url = new URL("http://images.detik.com/content/2014/11/06/10/194814_022933_judionline.jpg");
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL("http://images.detik.com/content/2014/11/06/10/194814_022933_judionline.jpg").getContent());
//            i.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute("http://images.detik.com/content/2014/11/06/10/194814_022933_judionline.jpg");
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

//    public static Bitmap loadBitmap(String url) {
//        Bitmap bitmap = null;
//        InputStream in = null;
//        BufferedOutputStream out = null;
//
//        try {
//            in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);
//
//            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
//            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
//            copy(in, out);
//            out.flush();
//
//            final byte[] data = dataStream.toByteArray();
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            //options.inSampleSize = 1;
//
//            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,options);
//        } catch (IOException e) {
//            Log.e(TAG, "Could not load Bitmap from: " + url);
//        } finally {
//            closeStream(in);
//            closeStream(out);
//        }
//
//        return bitmap;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

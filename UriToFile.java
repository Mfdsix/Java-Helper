package btn.pp.claps.rdy.utils;

import android.content.Context;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileHelper {

    public File fileFromContentUri(Context context, Uri contentUri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(contentUri);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            String filename = "temp_file_" + (System.currentTimeMillis() / 1000L);
            File dir = context.getFilesDir();
            File file = new File(dir, filename);

            OutputStream outStream = new FileOutputStream(file);
            outStream.write(buffer.toByteArray());

            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

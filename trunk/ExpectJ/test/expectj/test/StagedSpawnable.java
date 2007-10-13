package expectj.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import expectj.ExpectJException;
import expectj.Spawnable;

/**
 * A spawnable implementation using a {@link StagedStringProducer} to produce data on
 * its stdout stream.
 * @author johan.walles@gmail.com
 */
public class StagedSpawnable implements Spawnable
{
    /**
     * The array of strings we will produce.
     */
    private String produceUs[];
    
    /**
     * Construct a staged string spawn.  The spawn will produce the requested strings
     * on its {@link #getInputStream()} stream.
     * <p>
     * Strings will be produced with a 500ms delay between each.  There will be no delay
     * before the first or after the last one.  A null entry means "don't create any
     * string here".
     * 
     * @see #StagedSpawnable(String...)
     * @param stringsToProduce The strings to produce.
     */
    public StagedSpawnable(String ... stringsToProduce) {
        this.produceUs = stringsToProduce;
    }
    
    public void stop() {
        // This method intentionally left blank
    }

    public void start() {
        // This method intentionally left blank
    }

    public boolean isClosed() {
        return false;
    }

    public OutputStream getOutputStream() {
        return null;
    }

    public InputStream getInputStream() {
        try {
            return new StagedStringProducer(this.produceUs).getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public int getExitValue() throws ExpectJException {
        return 0;
    }

    public InputStream getErrorStream() {
        return null;
    }
}

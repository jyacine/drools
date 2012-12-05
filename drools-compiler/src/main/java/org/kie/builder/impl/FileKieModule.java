package org.kie.builder.impl;

import org.drools.core.util.IoUtils;
import org.kie.builder.GAV;
import org.kie.builder.KieModuleModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import static org.drools.core.util.IoUtils.readBytesFromInputStream;

public class FileKieModule extends AbstractKieModule implements InternalKieModule {
    private final File             file;   

    public FileKieModule(GAV gav,
                      KieModuleModel kieProject,
                      File file) {
        super( gav, kieProject );
        this.file = file;
    }

    @Override
    public File getFile() {
        return this.file;
    }    


    @Override
    public boolean isAvailable(String pResourceName) {
        return new File( file, pResourceName).exists();
    }


    @Override
    public byte[] getBytes(String pResourceName) {
        try {
            return IoUtils.readBytesFromInputStream( new FileInputStream( new File( file, pResourceName) ) );
        } catch ( Exception e ) {
            throw new RuntimeException("Unable to get bytes for: " + new File( file, pResourceName) );
        }
    }


    @Override
    public Collection<String> getFileNames() {
        return IoUtils.recursiveListFile( file );
    }


    @Override
    public byte[] getBytes() {
        try {
            return readBytesFromInputStream(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "FileKieModule[ GAV=" + getGAV() + "file=" + file + "]";
    }

}
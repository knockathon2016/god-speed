package ginie.boot;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

/**
 * Created by dhruvr
 */
public class FileExistsValidator implements IValueValidator<File> {

    public void validate(String name, File value) throws ParameterException {
        if(!value.exists()){
            throw new ParameterException(String.format("File %s doesn't exist", value));
        }
    }
}

package com.example.springbatchunivocity.services.readFilesService.readFilesServiceImpl;

import com.example.springbatchunivocity.model.Student;
import com.example.springbatchunivocity.services.readFilesService.Reader;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvRoutines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;
@Component
public class ReaderCsv implements Reader {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    private Iterator<Student> iterator;
    @Override
    public ItemReader<Student> readFile(String path) {
        return () -> {
            try{
                File file=new File(path);
                if(iterator == null){
                    CsvParserSettings parserSettings = new CsvParserSettings();
                    CsvRoutines routines = new CsvRoutines(parserSettings);
                    iterator = routines.iterate(Student.class, file).iterator();
                }
                if(iterator.hasNext()){
                    return iterator.next();
                } else {
                    iterator = null;
                }
            }catch (RuntimeException exception){
                logger.error(exception.getMessage());
            }

            return null;
        };
    }
}

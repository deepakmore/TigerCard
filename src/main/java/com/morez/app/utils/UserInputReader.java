package com.morez.app.utils;

import com.google.common.collect.Lists;
import com.morez.app.constants.CardConstants;
import com.morez.app.model.Trip;
import com.morez.app.utils.exceptions.FileExtensionNotMatched;
import com.morez.app.utils.exceptions.InvalidFileException;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.morez.app.constants.CardConstants.BLANK_SPACE;
import static com.morez.app.constants.CardConstants.DOT;
import static java.util.stream.Collectors.toList;

@UtilityClass
public class UserInputReader {

    public List<Trip> captureUserTripsFromTextFile(File inputFile) {
        return mapToTrip(readFile(inputFile));
    }

    protected List<String> readFile(File inputFile) {
        List<String> lines = Lists.newArrayList();
        if (isTxtFile(inputFile)) {
            try (BufferedReader bufferReader = new BufferedReader(new FileReader(inputFile))) {
                String line = null;
                while ((line = bufferReader.readLine()) != null) {
                    if(!line.trim().isEmpty())
                        lines.add(line.trim());
                }
            } catch (IOException e) {
                throw new InvalidFileException("File is not valid");
            }
        } else
            throw new FileExtensionNotMatched("File Extension does not match");

        return lines;
    }

    protected boolean isTxtFile(File inputFile) {
        return inputFile.getAbsolutePath().substring(
                inputFile.getAbsolutePath().lastIndexOf(DOT)).equals(CardConstants.TEXT_FILE_EXTENSION);
    }

    protected List<Trip> mapToTrip(List<String> lines) {
        return lines.stream()
                .map(s -> new Trip(s.split(BLANK_SPACE)))
                .collect(toList());
    }
}

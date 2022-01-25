package com.morez.app.utils;

import com.morez.app.model.Trip;
import com.morez.app.utils.exceptions.FileExtensionNotMatched;
import com.morez.app.utils.exceptions.InvalidFileException;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static com.morez.app.constants.CardConstants.BLANK_SPACE;
import static org.junit.Assert.assertEquals;

public class UserInputReaderTest {

    private static final String TEST_DAILY_INPUT_FILE = "test-daily.txt";
    private static final String TEST_WEEKLY_INPUT_FILE = "test-weekly.txt";
    private static final String TEST_DOCX_FILE = "test-daily.docx";
    private static final String TEST_INVALID_FILE = "test-invalid.txt";

    private static final int TOTAL_LINES_DAILY_INPUT_FILE = 6;
    private static final int TOTAL_LINES_WEEKLY_INPUT_FILE_AFTER_BLANK_LINES = 40;

    @Test(expected = FileExtensionNotMatched.class)
    public void shouldRaiseExceptionIfFileIsNotTxtFile() {
        File file = new File(TEST_DOCX_FILE);
        UserInputReader.readFile(file);

    }

    @Test(expected = InvalidFileException.class)
    public void shouldRaiseExceptionIfFileIsNotValid() {
        File file = new File(TEST_INVALID_FILE);
        UserInputReader.readFile(file);
    }

    @Test
    public void shouldReturnLinesOfInputFile() {
        File file = new File(
                Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource(TEST_DAILY_INPUT_FILE))
                        .getFile()
        );

        List<String> actualLines = UserInputReader.readFile(file);
        assertEquals(TOTAL_LINES_DAILY_INPUT_FILE, actualLines.size());
    }

    @Test
    public void shouldReturnLinesOfInputFileWithoutBlankLines() {
        File file = new File(
                Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource(TEST_WEEKLY_INPUT_FILE))
                        .getFile()
        );

        List<String> actualLines = UserInputReader.readFile(file);
        assertEquals(TOTAL_LINES_WEEKLY_INPUT_FILE_AFTER_BLANK_LINES, actualLines.size());
    }

    @Test
    public void shouldMapInputFileLinesToTrips() {
        File file = new File(
                Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResource(TEST_DAILY_INPUT_FILE))
                        .getFile()
        );

        List<String> actualLines = UserInputReader.readFile(file);
        List<Trip> actualTrips = UserInputReader.mapToTrip(actualLines);

        assertEquals(actualLines.size(), actualTrips.size());
        for (int index = 0; index < actualLines.size(); index++) {
            verifyTrips(actualLines.get(index), actualTrips.get(index));
        }
    }

    private void verifyTrips(String actualLine, Trip actualTrip) {
        assertEquals(actualLine.split(BLANK_SPACE)[0], actualTrip.getDate().toString());
        assertEquals(actualLine.split(BLANK_SPACE)[1], actualTrip.getDay().name());
        assertEquals(actualLine.split(BLANK_SPACE)[2], actualTrip.getStartTime().toString());
        assertEquals(actualLine.split(BLANK_SPACE)[3], actualTrip.getSourceZone());
        assertEquals(actualLine.split(BLANK_SPACE)[4], actualTrip.getDestinationZone());
    }
}

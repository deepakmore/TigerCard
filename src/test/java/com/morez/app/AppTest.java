package com.morez.app;

import static com.morez.app.constants.CardConstants.TEST_DAILY_INPUT_FILE;
import static com.morez.app.constants.CardConstants.TEST_WEEKLY_INPUT_FILE;
import org.junit.Test;

public class AppTest {

    @Test
    public void shouldReturnOutputForDailyTrips() {
        App.main(new String[] {TEST_DAILY_INPUT_FILE});
    }

    @Test
    public void shouldReturnOutputForWeeklyTrips() {
        App.main(new String[] {TEST_WEEKLY_INPUT_FILE});
    }
}

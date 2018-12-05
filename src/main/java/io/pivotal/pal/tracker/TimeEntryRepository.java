package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    TimeEntry create(TimeEntry timeEntry);
    TimeEntry find(long id);
    void delete(long id);
    List<TimeEntry> list();
    TimeEntry update(long id, TimeEntry timeEntry);
}

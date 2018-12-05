package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<Long, TimeEntry>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long newId = timeEntryMap.size()+1;
        timeEntry.setId(newId);
        timeEntryMap.put(newId, timeEntry);

        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public void delete(long id) { timeEntryMap.remove(id);}

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntryFound = find(id);
        if(timeEntryFound != null){
            timeEntry.setId(id);
            timeEntryMap.put(id, timeEntry);
        }

        return timeEntryMap.get(id);
    }
}

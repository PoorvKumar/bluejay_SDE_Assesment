import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Employee
{
    private String posId;
    private String posStatus;
    private String name;
    private String fileNumber;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private Duration timecardHours;
    private LocalDate payCycleStartDate;
    private LocalDate payCycleEndDate;

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getPosStatus() {
        return posStatus;
    }

    public void setPosStatus(String posStatus) {
        this.posStatus = posStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    // Define a DateTimeFormatter for parsing date and time strings
    private static final DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // Setter methods for the Employee class
    public void setTimeIn(String timeInStr)
    {
        if(timeInStr==null || timeInStr.trim().isEmpty())
        {
            this.timeIn=null;
            return ;
        }

        try {
            this.timeIn = LocalDateTime.parse(timeInStr, dateTimeformatter);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            e.printStackTrace();
        }
    }

    public void setTimeOut(String timeOutStr)
    {
        if(timeOutStr==null || timeOutStr.trim().isEmpty())
        {
            this.timeOut=null;
            return ;
        }

        try {
            this.timeOut = LocalDateTime.parse(timeOutStr, dateTimeformatter);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            e.printStackTrace();
        }
    }

    public void setTimecardHours(String timecardHoursStr)
    {
        if(timecardHoursStr==null || timecardHoursStr.trim().isEmpty())
        {
            this.timecardHours=null;
            return ;
        }

        // Parse the time duration string and convert it to a Duration object
        String[] parts = timecardHoursStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        this.timecardHours = Duration.ofHours(hours).plusMinutes(minutes);
    }

    public void setPayCycleStartDate(String startDateStr)
    {
        if(startDateStr==null || startDateStr.trim().isEmpty())
        {
            this.payCycleStartDate=null;
            return ;
        }

        try {
            this.payCycleStartDate = LocalDate.parse(startDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            e.printStackTrace();
        }
    }

    public void setPayCycleEndDate(String endDateStr)
    {
        if(endDateStr==null || endDateStr.trim().isEmpty())
        {
            this.payCycleEndDate=null;
            return ;
        }

        try {
            this.payCycleEndDate = LocalDate.parse(endDateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            e.printStackTrace();
        }
    }
    public long getNumberofDaysWorked()
    {
        if (payCycleStartDate == null || payCycleEndDate == null) {
            return 0; // Handle cases where payCycleStartDate or payCycleEndDate is not set
        }

        // Calculate the number of days between payCycleStartDate and payCycleEndDate
        long daysWorked = ChronoUnit.DAYS.between(payCycleStartDate, payCycleEndDate) + 1;

        return daysWorked;
    }

    public double getDurationHours()
    {
        if (timecardHours == null) {
            return 0; // Handle cases where timecardHours is not set
        }

        long hoursWorked = timecardHours.toHours();
        return hoursWorked;

//        if (timeIn == null || timeOut == null) {
//            return 0.0; // Handle cases where timeIn or timeOut is not set
//        }
//
//        // Calculate the duration in seconds between timeIn and timeOut
//        long secondsWorked = Duration.between(timeIn, timeOut).getSeconds();
//
//        // Convert seconds to hours (3600 seconds in an hour)
//        double hoursWorked = (double) secondsWorked / 3600.0;
//
//        return hoursWorked;
    }

    public String toString()
    {
        return "Name: "+this.name+", PositionId: "+this.posId+", Position Status: "+this.posStatus;
    }
}

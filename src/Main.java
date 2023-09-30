import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        String fileName="Assignment_Timecard.csv";

        try
        {
            BufferedReader reader=new BufferedReader(new FileReader(fileName));
            String line;
            boolean skipHeader=true;

            ArrayList<Employee> employees=new ArrayList<Employee>();

            while((line=reader.readLine())!=null)
            {
                if(skipHeader)
                {
                    skipHeader=false;
                    continue;
                }

                String columns[]=parseCSVLine(line);

                Employee employee=new Employee();
                employee.setPosId(columns[0]);
                employee.setPosStatus(columns[1]);
                employee.setTimeIn(columns[2]);
                employee.setTimeOut(columns[3]);
                employee.setTimecardHours(columns[4]);
                employee.setPayCycleStartDate(columns[5]);
                employee.setPayCycleEndDate(columns[6]);
                employee.setName(columns[7]);
                employee.setFileNumber(columns[8]);

//                System.out.println(employee.getNumberofDaysWorked());

                employees.add(employee);
            }

            //worked consecutively 7 days
            Map<String,Employee> empMap1=new HashMap<>();
            for(Employee emp:employees)
            {
                if(emp.getNumberofDaysWorked()>=7)
                {
                    empMap1.put(emp.getPosId(),emp);
                }
            }

            System.out.println("Employees who worked for 7 days consecutively are:  "+empMap1.size());
            for(Map.Entry<String,Employee> entry:empMap1.entrySet())
            {
                System.out.println(entry.getValue()); //toString() function overidded in class Employee
            }
            System.out.println();

            //worked upto 10hours in shift
            Map<String,Employee> empMap2=new HashMap<>();
            for(Employee emp:employees)
            {
                if(emp.getDurationHours()<=10 && emp.getDurationHours()>=1)
                {
                    empMap2.put(emp.getPosId(),emp);
                }
            }

            System.out.println("Employees who worked for less than 10 but greater than 1 hours time in shifts are: "+empMap2.size());
            for(Map.Entry<String,Employee> entry:empMap2.entrySet())
            {
                System.out.println(entry.getValue()); //toString() function overidded in class Employee
            }
            System.out.println();

            //worked more than 14 hours in shift
            Map<String,Employee> empMap3=new HashMap<>();
            for(Employee emp:employees)
            {
                if(emp.getDurationHours()>14)
                {
                    empMap3.put(emp.getPosId(),emp);
                }
            }

            System.out.println("Employees who worked more than 14 hours time in shifts are: "+empMap3.size());
            for(Map.Entry<String,Employee> entry:empMap3.entrySet())
            {
                System.out.println(entry.getValue()); //toString() function overidded in class Employee
            }
            System.out.println();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String[] parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean insideQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                insideQuotes = !insideQuotes;
            } else if (c == ',' && !insideQuotes) {
                values.add(currentValue.toString());
                currentValue.setLength(0);
            } else {
                currentValue.append(c);
            }
        }

        values.add(currentValue.toString());
        return values.toArray(new String[0]);
    }
}

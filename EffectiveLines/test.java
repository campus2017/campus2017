package user.testinterface;

/**
 * Created by Administrator on 2017/5/12.
 */
public class Employee implements Comparable<Employee>
{
    private String name;
    private double salary;
    public Employee(String n, double s){
        name = n;

        salary = s;
    }
    public String getName(){
        return name;
    }
    public double getSalary(){
        return salary;
    }
    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }
    @Override
    public int compareTo(Employee other){
        return Double.compare(salary,other.salary);
    }

}
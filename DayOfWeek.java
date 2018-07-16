import java.time.LocalDate;
import java.util.Calendar;
import java.lang.Math;
public class DayOfWeek
{
	public static LocalDate localDate = LocalDate.now();
	public int da;
	public int mont;
	public int ye;
	public int WeekOne(int ye)
	{
		int starting = 0,i;
		for (i=1;i<=30;i++)
		{
			if (Determine(i,9,ye)=="Mon")
				{
						starting=i;
				}
		}
		return starting;
	}
	public int WeekOrder(int da, int mont, int ye)
	{
		int next;
		if (mont<9||(mont==9&&WeekOne(ye)<da))
		{
			next=ye-1;
		}
		else
		{
			next=ye;
		}
			return Math.floorDiv(Math.abs((Remainder(ye,mont,da)-Remainder(next,9,WeekOne(next))))-1,7);
	}
	public String Determine(int da, int mont, int ya)
	{
		int diff,meter = 0,difference;
		String Determine = null;
		if (leap(ye)&&mont==2&&da==29)
		{
			da=1;
			mont=3;
		}
		difference=(int) Math.abs(PastOrFuture(da,mont,ye)*Remainder(localDate.getYear(),Calendar.MONTH,localDate.getDayOfMonth())-PastOrFuture(da,mont,ye)*Remainder(ye,mont,da))+middle(ye);
		switch (Math.abs(Remainder(localDate.getYear(),Calendar.MONTH,localDate.getDayOfMonth()-Remainder(1972,12,16))+middle(1972))%7)
		{
		case 0:
		{
			diff=6;
		}
		case 1:
		{
			diff=7;
		}
		default:
		{
			diff=Math.abs(Remainder(localDate.getYear(),Calendar.MONTH,localDate.getDayOfMonth()-Remainder(1972,12,16))+middle(1972))%7-1;
		}
		}
		if (PastOrFuture(da,mont,ye)==1)
		{
			if (Math.signum(diff-difference%7)==1)
			{
			meter=Math.abs(diff-difference%7);
			}
			else
			{
				meter=7-Math.abs(diff-difference%7);
			}
		}
		else  if (PastOrFuture(da,mont,ye)==-1)
		{
			meter=diff+difference%7;
			if (meter>7)
			{
				meter=meter-7;
			}
		}
		switch (meter)
		{
		case 0:
		{
			Determine= "Sun";
		}
		case 1:
		{
			Determine= "Mon";
		}
		case 2:
		{
			Determine= "Tue";
		}
		case 3:
		{
			Determine= "Wed";
		}
		case 4:
		{
			Determine= "Thu";
		}
		case 5:
		{
			Determine= "Fri";
		}
		case 6:
		{
			Determine= "Sat";
		}
		case 7:
		{
			Determine= "Sun";
		}
		}
		return Determine;
	}
	public Boolean leap (int ya)
	{
		if (ya%4==0)
		{
			if (ya%100==0)
			{
				if (ya%400==0)
				{
					return true;
				}
				else
				{
				return false;
				}
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	public int Remainder(int ya, int mon, int da)
	{
		int Remainder = 0,i;
		for (i=1;i<mon;i++)
		{
			switch (i)
			{
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			{
				Remainder=Remainder+31;
			}
			case 4: case 6: case 9: case 11:
			{
				Remainder=Remainder+30;
			}
			case 2:
			{
				if (leap(ya)==true)
				{
				Remainder=Remainder+29;
				}
				else
				{
					Remainder=Remainder+28;
				}
			}
			}
		}
		Remainder=Remainder+da;
		return Remainder;
	}
	public int middle(int ye)
	{
		int i,middle = 0;
		
		for (i=(int) (ye+PastOrFuture(da,mont,ye));PastOrFuture(da,mont,ye)*i<=PastOrFuture(da,mont,ye)*localDate.getYear();i=(int) (i+PastOrFuture(da,mont,ye)))
		{
			if (leap(i))
			{
				middle=middle+366;
			}
			else
			{
				middle=middle+365;
			}
		}
		return middle;
	}
	public double PastOrFuture(int da,int mont, int ye)
	{
		double a;
		
		a=Math.signum(localDate.getYear()-ye);
		if (a==0.0)
		{
			a=Math.signum(Calendar.MONTH-mont);
		}
			if (a==0)
			{
				a=Math.signum(localDate.getDayOfMonth()-da);
			}
				if (a==0)
				{
					a=1;
				}
		return a;
	}
	    }

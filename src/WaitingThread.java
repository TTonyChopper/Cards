public class WaitingThread extends Thread
{
	boolean run;
	public WaitingThread()
	{
		run=true;
		this.start();  
	}
	public void run()
	{ 
		System.out.print("Waiting");
		try
		{
			sleep(1000);
		}
		catch(Exception e)
		{
		}
		while(run)
		{
			System.out.print(".");
			try
			{
				sleep(1200);
			}
			catch(Exception e)
			{
			}
		}
	}
	public void stopT()
	{
		run=false; 
	}
}
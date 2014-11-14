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
		int i = 1;
		while(run)
		{
			
			if (i >= 30){
				System.out.print("\nWaiting");
				i=0;
			} else System.out.print(".");
			try
			{
				sleep(1200);
			}
			catch(Exception e)
			{
			}
			i++;
		}
	}
	public void stopT()
	{
		run=false; 
	}
}

package com.kp.appdirect.manage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.util.thread.ThreadPool;


//import these packages for jetty 6

/*import org.mortbay.component.LifeCycle;
import org.mortbay.log.Log*/;


/** 
 * This class wraps a {@link ThreadPoolExecutor} with the {@link org.mortbay.thread.ThreadPool} and 
 * {@link LifeCycle} interfaces so that it may be used by the Jetty {@link org.mortbay.jetty.Server}
 * 
 * @author aayush.bhatnagar
 *
 */
public class JettyThreadPool extends ThreadPoolExecutor implements ThreadPool, LifeCycle
{

    /** Default constructor.
     * Core size is 8, max pool size is 64, pool thread timeout after 60 seconds and
     * an unbounded {@link LinkedBlockingQueue} is used for the job queue;
     */
    public JettyThreadPool()
    {
        super(8,12,60,TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    }
    
    /* ------------------------------------------------------------ */
    /** Default constructor.
     * Core size is 8, max pool size is 256, pool thread timeout after 60 seconds
     * @param queueSize if -1, an unbounded {@link LinkedBlockingQueue} is used, if 0 then a
     * {@link SynchronousQueue} is used, other a {@link ArrayBlockingQueue} of the given size is used.
     */
    public JettyThreadPool(int queueSize)
    {
        super(8,64,60,TimeUnit.SECONDS,
                queueSize<0?new LinkedBlockingQueue<Runnable>()
                        : (queueSize==0?new SynchronousQueue<Runnable>()
                                :new ArrayBlockingQueue<Runnable>(queueSize)));
    }

    /** Size constructor.
     * an unbounded {@link LinkedBlockingQueue} is used for the jobs queue;
     */
    public JettyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit)
    {
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,new LinkedBlockingQueue<Runnable>());
    }

    public JettyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue)
    {
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
    }

    public JettyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
    {
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,handler);
    }

    public JettyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
    {
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);
    }

    public JettyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)
    {
        super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory);
    }

    public boolean dispatch(Runnable job)
    {
        try
        {       
            execute(job);
            return true;
        }
        catch(RejectedExecutionException e)
        {
//            Log.warn(e);
            return false;
        }
    }

    public int getIdleThreads()
    {
        return getPoolSize()-getActiveCount();
    }

    public int getThreads()
    {
        return getPoolSize();
    }

    public boolean isLowOnThreads()
    {
        return getActiveCount()>=getMaximumPoolSize();
    }

    public void join() throws InterruptedException
    {
        this.awaitTermination(Long.MAX_VALUE,TimeUnit.MILLISECONDS);
    }

    public boolean isFailed()
    {
        return false;
    }

    public boolean isRunning()
    {
        return !isTerminated() && !isTerminating();
    }

    public boolean isStarted()
    {
        return !isTerminated() && !isTerminating();
    }

    public boolean isStarting()
    {
        return false;
    }

    public boolean isStopped()
    {
        return isTerminated();
    }

    public boolean isStopping()
    {
        return isTerminating();
    }


    public void start() throws Exception
    {
        if (isTerminated() || isTerminating() || isShutdown())
            throw new IllegalStateException("Cannot restart");
    }


    public void stop() throws Exception
    {
        super.shutdown();
        if (!super.awaitTermination(60,TimeUnit.SECONDS))
            super.shutdownNow();
    }


    public void addLifeCycleListener(LifeCycle.Listener listener)
    {
        throw new UnsupportedOperationException();
    }
    

    public void removeLifeCycleListener(LifeCycle.Listener listener)
    {
    }
    
}


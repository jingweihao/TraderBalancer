package com.balancerLL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Level;

import org.apache.cxf.clustering.AbstractStaticFailoverStrategy;
import org.apache.cxf.endpoint.Endpoint;

/**
 * load balance strategy based on a randomized walk through the
 * static cluster represented by multiple endpoints associated
 * with the same service instance.
 *
 */
public class LCLoadBalanceStrategy extends NewAbstractStaticFailoverStrategy {
    
    private Random random;
    private String type;
    private int estimatenum;
    
    /**
     * Constructor.
     */
    
//    public Endpoint selectAlternateEndpoint(List<Endpoint> alternates) {
//        Endpoint selected = null;
//        if (alternates != null && alternates.size() > 0) {
//            selected = getNextAlternate(alternates);
//            Level level = getLogLevel();
//            if (LOG.isLoggable(level)) {
//                LOG.log(level,
//                        "FAILING_OVER_TO_ALTERNATE_ENDPOINT",
//                         new Object[] {selected.getEndpointInfo().getName(),
//                                       selected.getEndpointInfo().getAddress()});
//            }
//        } else {
//            LOG.warning("NO_ALTERNATE_TARGETS_REMAIN");
//        }
//        return selected;
//    }
    
    
    
    public LCLoadBalanceStrategy() {
//        random = new Random();
    	estimatenum = 0;
        
    }
    
    public void setType(String type)
    {
    	this.type = type;
    }
    

    /**
     * Get next alternate endpoint.
     * 
     * @param alternates non-empty List of alternate endpoints 
     * @return
     */
    protected <T> T getNextAlternate(List<T> alternates) 
    {
    	System.out.println("start get next alternate============");
    	this.type = alternates.get(0).toString().substring(36,alternates.get(0).toString().length());
    	//System.out.println("alternate address:======" + alternates);
    	
    	
    	// alternates current good address or initial address
    	// check map
    	
    	getlistsingleton allAddress = getlistsingleton.getInstance();
    	
    	System.out.println("current record connections:=======");
    	allAddress.printconn();
    	
    	PriorityQueue<Servers> queue = allAddress.getQueue();
    	Servers nextserver = queue.remove();
    	String address = nextserver.getAddress();
    	int connections = nextserver.getConnections();
    	System.out.println("min connections: "+ connections);
    	System.out.println("min address: "+ address);
    	
    	List<Servers> notexist = new ArrayList<Servers>();
    	
    	while(!alternates.contains(address+"/"+type))
    	{
    		System.out.println("not exist: " + address);
    		notexist.add(nextserver);
    		nextserver = queue.remove();
    		address = nextserver.getAddress();	
    		
    		connections = nextserver.getConnections();
        	System.out.println("new min connections: "+ connections);
        	
    		System.out.println("new min address:" + address);
    	}
    	
    	System.out.println("selected end:"+ address);
    	
    	for(int i = 0; i < notexist.size(); i++)
    	{
    		queue.add(notexist.get(i));
    	}
    	
    	allAddress.setQueue(queue);
    	
    	// Least connection just add one connection;
    	
//    	allAddress.addconnection(address, 1);
    	
    	
    	// Least Load connection
    	
    	System.out.println("type" + type);
    	
    	
    	int num = getestimatenum();
    	allAddress.addconnection(address, num);
    	System.out.println("estimate:===="+ num);
    	
//    	allAddress.addconnection(address);
    	
    	
    	
//    	System.out.println("after add:===========");
//    	allAddress.printqueue();
    	
//    	System.out.println("selected server address:===========");
//    	System.out.println(address);

    	String newaddress = address + "/"+ type;
    	System.out.println("entire address with type");
    	System.out.println(newaddress);
    	
    	System.out.println("now record connections:  ");
    	allAddress.printconn();
    	System.out.println();
    	
//    	alternates.remove(newaddress);
    	

    	
    	return (T)newaddress;
//        return alternates.get(random.nextInt(alternates.size()));
    }

    public void setestimatenum(int num)
    {
    	this.estimatenum = num;
    }
	@Override
	public int getestimatenum() {
		// TODO Auto-generated method stub
		
		return estimatenum;
	}

	@Override
	public String gettype() {
		// TODO Auto-generated method stub
		return this.type;
	}
    
    
    
    
    

    
}

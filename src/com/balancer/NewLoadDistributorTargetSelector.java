/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.balancer;

import java.util.List;
import java.util.logging.Logger;

import org.apache.cxf.clustering.FailoverTargetSelector;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;

import com.balancer.NewFailoverTargetSelector.InvocationContext;
import com.balancer.NewFailoverTargetSelector.InvocationKey;

/**
 * The LoadDistributorTargetSelector attempts to do the same job as the
 * FailoverTargetSelector, but to choose an alternate target on every request
 * rather than just when a fault occurs.
 * The LoadDistributorTargetSelector uses the same FailoverStrategy interface as 
 * the FailoverTargetSelector, but has a few significant limitations:
 * 1. Because the LoadDistributorTargetSelector needs to maintain a list of targets
 *    between calls it has to obtain that list without reference to a Message.
 *    Most FailoverStrategy classes can support this for addresses, but it cannot
 *    be supported for endpoints.
 *    If the list of targets cannot be obtained without reference to a Message then
 *    the list will still be obtained but it will be specific to the Message and thus
 *    discarded after this message has been processed.  As a consequence, if the
 *    strategy chosen is a simple sequential one the first item in the list will
 *    be chosen every time.
 *    Conclusion: Be aware that if you are working with targets that are 
 *    dependent on the Message the process will be less efficient and that the
 *    SequentialStrategy will not distribute the load at all.
 * 2. The AbstractStaticFailoverStrategy base class excludes the 'default' endpoint
 *    from the list of alternate endpoints.
 *    If alternate endpoints (as opposed to alternate addresses) are to be used
 *    you should probably ensure that your FailoverStrategy overrides getAlternateEndpoints
 *    and calls getEndpoints with acceptCandidatesWithSameAddress = true.
 */
public class NewLoadDistributorTargetSelector extends NewFailoverTargetSelector {
    private static final Logger LOG = LogUtils.getL7dLogger(
                        NewLoadDistributorTargetSelector.class);
    private static final String IS_DISTRIBUTED = 
            "org.apache.cxf.clustering.LoadDistributorTargetSelector.IS_DISTRIBUTED";

    private List<String> addressList;

    private boolean failover = true;

    /**
     * Normal constructor.
     */
    public NewLoadDistributorTargetSelector() {
        super();
    }

    /**
     * Constructor, allowing a specific conduit to override normal selection.
     *
     * @param c specific conduit
     */
    public NewLoadDistributorTargetSelector(Conduit c) {
        super(c);
    }

    public boolean isFailover() {
        return failover;
    }

    public void setFailover(boolean failover) {
        this.failover = failover;
    }

    @Override
    protected java.util.logging.Logger getLogger() {
        return LOG;
    }

    /**
     * Called when a Conduit is actually required.
     *
     * @param message
     * @return the Conduit to use for mediation of the message
     */
    public synchronized Conduit selectConduit(Message message) 
    {
    	System.out.println("select conduit==========");
        Conduit c = message.get(Conduit.class);
        if (c != null) {
            return c;
        }
        Exchange exchange = message.getExchange();
        InvocationKey key = new InvocationKey(exchange);
        InvocationContext invocation = inProgress.get(key);
        if ((invocation != null) && !invocation.getContext().containsKey(IS_DISTRIBUTED)) {
            Endpoint target = getDistributionTarget(exchange, invocation);
            
            System.out.println("endpoiint address" + target.getEndpointInfo().getAddress());
            
            if (target != null) 
            {
            	System.out.println("target != null");
                setEndpoint(target);
                message.put(Message.ENDPOINT_ADDRESS, target.getEndpointInfo().getAddress());
                message.put(CONDUIT_COMPARE_FULL_URL, Boolean.TRUE);
                overrideAddressProperty(invocation.getContext());
                invocation.getContext().put(IS_DISTRIBUTED, null);
            }
        }
        return getSelectedConduit(message);
    }

    /**
     * Get the failover target endpoint, if a suitable one is available.
     *
     * @param exchange the current Exchange
     * @param invocation the current InvocationContext
     * @return a failover endpoint if one is available
     * 
     * Note: The only difference between this and the super implementation is
     * that the current (failed) address is removed from the list set of alternates,
     * it could be argued that that change should be in the super implementation
     * but I'm not sure of the impact.
     */
    
    protected Endpoint getFailoverTarget(Exchange exchange,
                                       InvocationContext invocation) 
    {
    	System.out.println("start load balance getfailovertarget=========");
        List<String> alternateAddresses = null;
        if (!invocation.hasAlternates()) 
        {
            // no previous failover attempt on this invocation
            //
        	System.out.println("not has alternativer=========");
            alternateAddresses = 
                getStrategy().getAlternateAddresses(exchange);
            if (alternateAddresses != null) {
                alternateAddresses.remove(exchange.getEndpoint().getEndpointInfo().getAddress());
                invocation.setAlternateAddresses(alternateAddresses);
            } else {
                invocation.setAlternateEndpoints(
                    getStrategy().getAlternateEndpoints(exchange));
            }
        }
        else 
        {
        	//¸Ä¸Ä-remove
        	System.out.println("hasalter=============");
            alternateAddresses = invocation.getAlternateAddresses();
            System.out.println("has address" + alternateAddresses);    
            String address = exchange.getEndpoint().getEndpointInfo().getAddress();
            System.out.println("should remove address:=====" + address);
            alternateAddresses.remove(address);            
            System.out.println("after remove:=====" + alternateAddresses);
        }

        
        System.out.println("starting==============");
        Endpoint failoverTarget = null;
        if (alternateAddresses != null) {
            String alternateAddress = 
                getStrategy().selectAlternateAddress(alternateAddresses);
            
            
            System.out.println(alternateAddress);
            
            
            if (alternateAddress != null) 
            {
            	System.out.println("reusing=============");
                // re-use current endpoint
                //
                failoverTarget = getEndpoint();

                failoverTarget.getEndpointInfo().setAddress(alternateAddress);
            }
        } else 
        {
        	System.out.println("====null=========");
            failoverTarget = getStrategy().selectAlternateEndpoint(
                                 invocation.getAlternateEndpoints());
        }
        System.out.println("address"+failoverTarget.getEndpointInfo().getAddress());
        return failoverTarget;
    }

    /**
     * Get the distribution target endpoint, if a suitable one is available.
     *
     * @param exchange the current Exchange
     * @param invocation the current InvocationContext
     * @return a distribution endpoint if one is available
     */
    private Endpoint getDistributionTarget(Exchange exchange,
                                           InvocationContext invocation) 
    {
//    	InvocationKey key= new InvocationKey(exchange);
//    	System.out.println();
//    	System.out.println("invocationcontext alternateendpoint address==========");
//    	for(int i = 0; i < invocation.getAlternateEndpoints().size(); i++)
//    	{
//    		System.out.println(invocation.getAlternateEndpoints().get(i).getEndpointInfo().getAddress());
//    	}
//    	System.out.println("invocationcontext alternative address==========" + invocation.getAlternateAddresses());
    	
//    	System.out.println("exchange key hashcode====="+ key.hashCode());
    	System.out.println("start getdistribute target=========");
    	
    	
    	
    	
        List<String> alternateAddresses = null;
        if ((addressList == null) || (addressList.isEmpty())) {
            try {
                addressList = getStrategy().getAlternateAddresses(null);
            } catch (NullPointerException ex) {
                getLogger().fine("Strategy " + getStrategy().getClass()
                        + " cannot handle a null argument to getAlternateAddresses: " + ex.toString());
            }
        }
        alternateAddresses = addressList;

        System.out.println("alternateaddresses====" + alternateAddresses);
        
        if ((alternateAddresses == null) || (alternateAddresses.isEmpty())) 
        {
        	System.out.println("alternateaddresses == null");
            alternateAddresses = getStrategy().getAlternateAddresses(exchange);
            if (alternateAddresses != null) {
                invocation.setAlternateAddresses(alternateAddresses);
            } else {
                invocation.setAlternateEndpoints(
                    getStrategy().getAlternateEndpoints(exchange));
            }
        }

        Endpoint distributionTarget = null;
        if ((alternateAddresses != null) && !alternateAddresses.isEmpty()) 
        {
        	System.out.println("alternateaddresses != null");
            String alternateAddress =
                getStrategy().selectAlternateAddress(alternateAddresses);
            
            System.out.println("selected address -===" + alternateAddress);
            
            if (alternateAddress != null) 
            {
                // re-use current endpoint
                distributionTarget = getEndpoint();
                distributionTarget.getEndpointInfo().setAddress(alternateAddress);
                System.out.println("now distributionTarget Endpoint address===" + alternateAddress);
            	
            	
                //not reuse endpoint
//            	Endpoint newendpoint = null;
//            	newendpoint.getEndpointInfo().setAddress(alternateAddress);
//            	distributionTarget = newendpoint;
                
                
                
            }
        } 
        else 
        {
            distributionTarget = getStrategy().selectAlternateEndpoint(
                                 invocation.getAlternateEndpoints());
            System.out.println("else now distributionTarget Endpoint address===" + distributionTarget.getEndpointInfo().getAddress());
        }
        
        System.out.println("finnally distributionTarget Endpoint address===" + distributionTarget.getEndpointInfo().getAddress());
        return distributionTarget;
    }

    @Override
    protected boolean requiresFailover(Exchange exchange, Exception ex) {
        return failover && super.requiresFailover(exchange, ex);
    }
}
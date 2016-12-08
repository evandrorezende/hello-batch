package com.example;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Named
public class SimpleItemWriter extends AbstractItemWriter {

	@Inject
	private JobContext jobContext;
	
	//@Inject
	//private EntityManager em;
	
	@Override
	public void writeItems(List<Object> list) throws Exception {
		for (Object obj : list) {
			
			PayrollRecord r = (PayrollRecord)obj;
            System.out.println("PayrollRecord: " + r);
            
            
         
            
            //em.persist(obj);
        }
	}

}

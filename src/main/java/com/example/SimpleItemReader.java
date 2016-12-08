package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SimpleItemReader extends AbstractItemReader {

	
	private int recordNumber;
	
	private String line;
	
	private BufferedReader br;
	
	private FileInputStream inputStream;
	
	@Inject
	private JobContext jobContext;
	
	public void open(Serializable prevCheckpointInfo) throws Exception{

		JobOperator jobOperator = BatchRuntime.getJobOperator();
		
		System.out.println("::EXEC_ID::"+jobContext.getExecutionId());
		
        Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
        String resourceName = (String) jobParameters.get("payrollInputDataFileName");
        inputStream = new FileInputStream(resourceName);        
        br = new BufferedReader(new InputStreamReader(inputStream));

        if (prevCheckpointInfo != null)
            recordNumber = (Integer) prevCheckpointInfo;
        
        for (int i=1; i<recordNumber; i++) {
            br.readLine();
        } 
        
        System.out.println("[SimpleItemReader] Opened Payroll file for reading from record number: "+prevCheckpointInfo);
	}
	
	
	@Override
	public Object readItem() throws Exception {
		Object record = null;
	       
		line = br.readLine();
		
		if (line != null) {
			System.out.println("::LER::"+line);
            String[] fields = line.split(";");
            PayrollInputRecord payrollInputRecord = new PayrollInputRecord();
            payrollInputRecord.setId(Integer.parseInt(fields[0]));
            payrollInputRecord.setBaseSalary(Integer.parseInt(fields[1]));
            record = payrollInputRecord;
        
           recordNumber++;
        }
		
        return record;
	}
	
	
	@Override
	public Serializable checkpointInfo() throws Exception {
	        return recordNumber;
	}

}

package com.example;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SimpleItemProcessor implements ItemProcessor {

	@Inject
	private JobContext jobContext;
	
	public Object processItem(Object obj) throws Exception {
		PayrollInputRecord inputRecord =
                (PayrollInputRecord) obj;
        PayrollRecord payrollRecord = 
                new PayrollRecord();

        int base = inputRecord.getBaseSalary();
        float tax = base * 27 / 100.0f;
        float bonus = base * 15 / 100.0f;

        payrollRecord.setEmpID(inputRecord.getId());
        payrollRecord.setBase(base);
        payrollRecord.setTax(tax);
        payrollRecord.setBonus(bonus);
        payrollRecord.setNet(base + bonus - tax);   
        return payrollRecord;
	}

}

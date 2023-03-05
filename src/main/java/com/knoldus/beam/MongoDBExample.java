package com.knoldus.beam;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.text.Document;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class MongoDBExample {
    public static void main(String[] args) {
        Pipeline p = Pipeline.create();
        PCollection<String> pInput = p.apply(TextIO.read().from("shashikant.csv"));
        PCollection<Document> pDocument = pInput.apply(ParDo.of(new DoFn<String, Document>() {

            @ProcessElement
            public void processElement(ProcessContext c){
             String[] arr = Objects.requireNonNull(c.element()).split(",");
             Map<String,Object> mapDocuments = new HashMap<String,Object>();
                //Map<String,Object> mapDocuments = new HashMap<>(String,Object)();
                mapDocuments.put("userId",arr[0]);
                mapDocuments.put("orderId",arr[1]);
                mapDocuments.put("name",arr[2]);
                mapDocuments.put("productId",arr[3]);
                mapDocuments.put("amount",arr[4]);
                mapDocuments.put("order_date",arr[5]);
                mapDocuments.put("country",arr[6]);

            }
        }));

        p.run();
    }
}

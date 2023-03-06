
## Beam-Integration-with-MongoDB
#### Description:: "In this template we will integrating Beam with mongoDB."

* Clone the Repo: 
````
git clone git@github.com:knoldus/Beam-Integration-with-MongoDB-master.git
````
>> main class 
> >
* follow path src/main/java/com.knoldus.beam/MongoDBExample

public class MongoDBExample {
public static void main(String[] args) {
Pipeline p = Pipeline.create();
PCollection<String> pInput = p.apply(TextIO.read().from("shashikant.csv"));
PCollection<Document> pDocument = pInput.apply(ParDo.of(new DoFn<String, Document>() {

            @ProcessElement
            public void processElement(ProcessContext c){
             String[] arr = Objects.requireNonNull(c.element()).split(",");
             Map<String,Object> mapDocuments = new HashMap<String,Object>();
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

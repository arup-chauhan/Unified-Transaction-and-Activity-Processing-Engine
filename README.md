# **Unified Transaction & Activity Processing Engine**

A low-latency system that aggregates and analyzes **large-scale transactional and activity data**, breaking down **data silos**
---

## **Table of Contents**

- [Project Overview](#project-overview)
- [Features](#features)
- [Real-World Applications](#real-world-applications)
- [System Architecture](#system-architecture)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Example Output](#example-output)
- [Test Output](#test-output)
- [Database](#database)
- [Docker Deployment](#docker-deployment)
- [Contributing](#contributing)

---

## **Project Overview**

The **Unified Transaction & Activity Processing Engine** integrates:

- **Hadoop MapReduce** for large-scale batch data processing.  
- **gRPC API** for low-latency access to aggregated insights.  
- **Dockerized services** for reproducibility and deployment across environments.  

It allows enterprises to unify **transactional** and **user activity logs** into a single queryable system.

---

## **Features**

- Aggregate **transactional and activity data** at scale.  
- Fault-tolerant and horizontally scalable MapReduce jobs.  
- **gRPC endpoints** for fast and reliable data queries.  
- Containerized deployment using **Docker**.  
- Designed to **eliminate data silos** by integrating multiple input sources.  

---

## **Real-World Applications**

This engine drives **data unification and analytics** across multiple industries. Key use cases include:

### **1. FinTech & Banking**  
- Analyze **transactions and user activity logs** for fraud detection.  
- Provide **real-time insights** for compliance and auditing.  

### **2. E-Commerce Platforms**  
- Combine **order transactions with user behavior** for trend analysis.  
- Generate **personalized product recommendations**.  

### **3. Media & Content Platforms**  
- Aggregate **content engagement logs** and subscription transactions.  
- Enable **low-latency dashboards** for editorial and ad insights.  

### **4. Enterprise Data Lakes**  
- Unify logs from **multiple departments** into one analytics engine.  
- Break down **organizational data silos** for better decision-making.  

---
## **System Architecture**

```plaintext
[ Transaction Logs ]      [ Activity Logs ]
        |                         |
        v                         v
    [ Hadoop MapReduce Processing Layer ]
                  |
                  v
            [ Aggregated Data ]
                  |
                  v
           [ gRPC API Service ]
                  |
                  v
            [ Client Queries ]
````
---
## **Installation**

### **Prerequisites**

* Java 11+
* Hadoop (local or cluster setup)
* Docker

### **Clone the Repository**

```bash
git clone https://github.com/Arup-Chauhan/Unified-Activity-Processing.git
cd Unified-Activity-Processing
```

---

### **Build the Project**

```bash
mvn clean package
```

---

### **Run in Docker**

```bash
docker build -t unified-activity-processing .
docker run -p 50051:50051 unified-activity-processing
```

---

## **Usage**

### **Submit Data to HDFS**

```bash
hdfs dfs -put /local/data/transactions.txt /input/transactions
hdfs dfs -put /local/data/activity.txt /input/activity
```

### **Trigger MapReduce Job**

```bash
hadoop jar target/unified-processing.jar com.project.Main /input /output
```

### **Query via gRPC**

Use a gRPC client (Postman/gRPC UI or generated stubs) to call:

* `GetTransactionSummary`
* `GetActivityTrends`
* `GetAnomalyReport`

---

## **API Endpoints**

* **GetTransactionSummary** → Returns aggregated transaction insights.
* **GetActivityTrends** → Provides activity metrics and trends.
* **GetAnomalyReport** → Detects outliers in logs.

(See `proto/` folder for detailed service definitions.)

### **Sample gRPC Proto Definition**

```proto
syntax = "proto3";

package unified;

service UnifiedProcessingService {
  rpc GetTransactionSummary (TransactionRequest) returns (TransactionSummary);
  rpc GetActivityTrends (ActivityRequest) returns (ActivityTrends);
  rpc GetAnomalyReport (AnomalyRequest) returns (AnomalyReport);
}

message TransactionRequest {
  string user_id = 1;
  string date_range = 2;
}

message TransactionSummary {
  int64 total_transactions = 1;
  double total_amount = 2;
}

message ActivityRequest {
  string user_id = 1;
  string date_range = 2;
}

message ActivityTrends {
  repeated string top_actions = 1;
  map<string, int32> action_counts = 2;
}

message AnomalyRequest {
  string source = 1;
}

message AnomalyReport {
  repeated string anomalies = 1;
}
```

---

### **Sample Java Client Usage**

```java
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import unified.UnifiedProcessingServiceGrpc;
import unified.Unified.TransactionRequest;
import unified.Unified.TransactionSummary;

public class ClientExample {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        UnifiedProcessingServiceGrpc.UnifiedProcessingServiceBlockingStub stub =
                UnifiedProcessingServiceGrpc.newBlockingStub(channel);

        TransactionRequest request = TransactionRequest.newBuilder()
                .setUserId("user123")
                .setDateRange("2023-01-01_to_2023-01-31")
                .build();

        TransactionSummary response = stub.getTransactionSummary(request);

        System.out.println("Total Transactions: " + response.getTotalTransactions());
        System.out.println("Total Amount: " + response.getTotalAmount());

        channel.shutdown();
    }
}
```

---

## **Example Output**

**Transaction Summary (Java Client):**

```plaintext
Total Transactions: 1543
Total Amount: 98234.75
```

**Activity Trends (Java Client):**

```plaintext
Top Actions: ["click", "purchase", "view"]
Action Counts: {click=1203, purchase=243, view=3109}
```

**Anomaly Report (Java Client):**

```plaintext
Anomalies: ["suspicious_login", "payment_failure"]
```

---
## **Database**

- **Raw Data**: Stored in **HDFS** for distributed processing with Hadoop MapReduce.  
- **Aggregated Results**: Persisted into **PostgreSQL** for structured queries, analytics, and integration with BI/reporting tools.  

This setup ensures the system leverages Hadoop’s scalability while still enabling **SQL-based insights** and easy downstream consumption.

### **Sample PostgreSQL Schema**

```sql
-- Table for storing raw/processed transactions
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    transaction_time TIMESTAMP NOT NULL
);

-- Table for storing user activity logs
CREATE TABLE activity_logs (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    action VARCHAR(255) NOT NULL,
    activity_time TIMESTAMP NOT NULL
);

-- Table for storing aggregated metrics
CREATE TABLE aggregated_metrics (
    id SERIAL PRIMARY KEY,
    metric_type VARCHAR(100) NOT NULL,  -- e.g. 'daily_summary', 'trend'
    metric_key VARCHAR(255) NOT NULL,   -- e.g. 'user123:2023-01-01'
    metric_value JSONB NOT NULL,        -- flexible storage for counts, sums, anomalies
    created_at TIMESTAMP DEFAULT NOW()
);
````

The **`aggregated_metrics`** table uses `JSONB` to flexibly store complex outputs (counts, anomaly lists, trend vectors) without changing schema every time.

---

## **Docker Deployment**

### **Docker Deployment Instructions**

1. **Build and Start Docker Container**:

   ```bash
   docker-compose up --build
   ```

2. **Verify Services**:

   ```bash
   docker ps
   ```

3. **Access gRPC Service**:

   ```
   localhost:50051
   ```

---

## **Contributing**

Big on Contributions! Fork the repository and submit a pull request.

---

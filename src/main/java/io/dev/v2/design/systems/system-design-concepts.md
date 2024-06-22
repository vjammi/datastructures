### System Design Concepts
    OSI
    Client-server model
    Network Protocols
    Storage
    Latency and Throughput
    Availability
    Cache
    Proxy
    Load Balancer
    Hashing
    Database
    Key-value store
    Specialized storage paradigms
    Replication and sharding
    Leader Election
    Peer to Peer
    Polling and Streaming
    Configuration
    Rate limiting
    Logging
    Pub/sub pattern — Stream
    MapReduce
    Security

Reference: https://itnext.io/system-design-concepts-that-helped-me-get-sr-frontend-offers-from-amazon-linkedin-9e100f3ce7d2#9629

### OSI Model and 7 Layers 
OSI Model
    
7 Layers
    Layer 1: Physical
    Layer 2: Data Link
    Layer 3: Network
    Layer 4: Transport
    Layer 5: Session
    Layer 6: Presentation
    Layer 7: Application
Data flow example

Reference: https://www.educative.io/blog/osi-model-layers?utm_campaign=brand_educative&utm_source=google&utm_medium=ppc&utm_content=performance_max&eid=5082902844932096&utm_term=&utm_campaign=%5BNew%5D+Performance+Max&utm_source=adwords&utm_medium=ppc&hsa_acc=5451446008&hsa_cam=18511913007&hsa_grp=&hsa_ad=&hsa_src=x&hsa_tgt=&hsa_kw=&hsa_mt=&hsa_net=adwords&hsa_ver=3&gclid=CjwKCAiAnZCdBhBmEiwA8nDQxTIbeAPHOAzW-_Yz99X-HGh5SiWdkzjp6aKS2suqXMK9_Fjk17mgLhoC3_UQAvD_BwE
            

Client-server model

    DNS — Domain Name System redirects the domain name to the IP address. The client sends a query to DNS and returns IP address;
    IP address — numeric address for each machine connected to the internet; a.b.c.d (0–255). 192.0.0.1 — localhost. 192.169..c.d
    Port — listening to multiple processes without colliding. 0–65525. 2¹⁶. 0–1023 ports are taken by the system (22: Secure Shell, 53: DNS lookup; 80: HTTP; 443: HTTPS)

Network Protocols

    IP — Internet protocol — rules to communicate between machines on the internet. Using IP Packets
    TCP — Transmission Control Protocol — guarantee packets are delivered in enrichedTransaction, will know if the failure happened. By additional TCP header (enrichedTransaction information), build on top of IP
    IP Packet — minimal data unit to communicate between machines. Consist of the header (source, recipient, size of the packet, version of protocol IPv4) and payload. Size from packet ²¹⁶ bytes. Can not guarantee all packets are received and ordered HTTP -> TCP -> IP

Storage

    Database — servers that store data in disk or memory. Record and query;
    Disk — stores and keeps data if the process dies. HDD — slower and cheaper. SSD — faster and more expensive, non-volatile;
    Memory — RAM fast access, volatile, erase data when the process dies;
    Persistent data — keeps data if the process dies (outage);

Latency and Throughput

    Latency — time requires to complete the operation (ms):
      1) Reading 1 Mb from RAM — 0.25 ms;
      2) Reading 1 Mb from SSD — 1 ms;
      3) Transfer 1 Mb through the network (1 Gb/s) — 10 ms;
      4) Reading 1 Mb from HDD — 20 ms;
      5) Transfer 1 Mb intercontinental round trip — 150 ms;

    Throughput — the number of the operating systems can handle per sec (RPS, QPS)

Availability — reliability

    Availability — Access of the service in point of time (per year); 1) 99% (87.8 hours); 2) 99.9% (8.8 hours); 3) 99.99% (52.6 min) 4) 99.999% (5.3 min) (5 nines);
    Redundancy — replication of the system to increase availability (make sure you don’t have a single point of failure — use redundancy). Passive redundancy (2 machines if one dies other works), active redundancy (if one dies another takes control of the function)
    SLA (service — level — agreement) guarantees of the service, consists of multiple SLO
    SLO (service — level — objective) guarantees of the service (availability)

Cache

    What can be cached — Client, Server, In Between (Server-Database ), API, Hardware (CPU). Operations that used frequently, heavy operation/computation;
    Cache — Hardware or software that stores the data to provide faster access (the result of the heavy computation, API responses, API requests);
    Write through cache — when the cache is updated in cache and memory synchronously, recover data if the process dies. Pros — simpler and reliable; cons — better works when not so many writes in cache;
    Write back cache — when cache updated in cache and memory asynchronously;
    If we care of staleness of cache (accuracy of data) -> we use local cache; if accuracy is important (views, likes) — use third party cache (Redis)
    Cache Hit— find requested data in the cache
    Cache miss — did not find stored cache because of pure design
    Cache eviction policy — rules when cached will be removed (LRU (recent), LFU (frequent), FIFO (queue))
    Content Delivery Network (CDN) — third party service that caches your server (based on region), always better latency. PoP (Point of Presence). Cloudflare, Google Cloud CDN.

Proxy — security, reliability

    Forward proxy — client-side (hide IP) VPN;
    Reverse proxy — server interceptor (load balancing, leader election) (logging, caching, filter requests, load balancing, security shield);
    Proxy tool — Nginx;

Load Balancer — security, reliability, performance

    Distribute traffic across the servers. Multiple load balancers with different selection strategies;
    Server selection strategy — (weight-if some servers more powerful) round-robin, random selection, performance-based, IP based, API path based;
    Hot-spot — distribute too much traffic to one machine, reason suboptimal sharding key/hashing function
    LB Library — Nginx;

Hashing

    Hashing — transforming a piece of data to hash — usually integer;
    Consistent hashing — minimize the number of keys to be remapped if the hash table is resized. (load balancer during adding or removing server) (Rendezvous — highest random hash);
    SHA — secure hash algorithm, the collection of hash functions. Popular SHA-3.

Database

    ACID — database (of database transaction): ATOMICITY — all operations in a transaction are all successful or failed, not an intermediate state; CONSISTENCY — each transaction guarantees that data is in the updated state (strong consistency), (eventual consistency) will be updated after some time; ISOLATION — execution of multiple transactions will be the same as they executed sequentially; DURABILITY — transactions are in the non-volatile state, they can not crash
    Relational database — database organized in tables, rows, and columns, and the relation between them.
    Nonrelational database — NOT tabular, specially organized for specific purposes.
    SQL — structured query language;
    SQL database — a relational database that supports SQL;
    NoSQL — a database that does not support SQL
    Database index — a data structure that improves the data retrieval, and slows the data write (because create an index for columns);
    Strong consistency — relates to ACID. (every transaction guarantee updated data) — PostgreSQL;
    Eventual consistency — (transaction will eventually (later) know the updated data);

Key-value store

    NoSQL database used for caching and dynamic configuration (Etcd — strong consistency, used for leader election), (Redis — caching, rate limiter), Zookeeper (strong consistency, leader election, highly available);

Specialized storage paradigms

    Blob Storage — key-value storage of Blob (binary large object) usually media: images, audio, video (Google Cloud Storage, Amazon S3);
    Time Series Database — store and analyze time-indexed data, created continuously (logging, internet of things, stocks ): Prometheus, InfluxDB, Graphite;
    Graph Database — when data has a deep relationship connection (social network) (Neo4j)
    Cypher — graph query language created by Neo4j, simpler than SQL
    Spatial database — location, map; uses quadtree for fast query (the region/location); (hotel, map)

Replication and sharding

    Replication — duplication of data from one server to another; (reduce single point of failure, reduce latency), main database and replica have to be updated (sync, async);
    Sharding — data partitioning (when the database is huge), splitting the database into shards to increase throughput. (based on client region, based on the type of data (payment), based on the hash of column). Can have Hot Spot if suboptimal sharding strategy

Leader Election — reliability

    The process when servers elect a leader server that performs primary operations (system important operations). And other nodes know who is the leader also after reelection;
    Consensus algorithms — choose the leader and synchronize all servers Paxos, Raft. Etcd and Zookeeper — key-value databases that implement leader election

Peer to Peer

    Machines divide the workload, increase system throughput. (file distribution)
    Gossip Protocol — the process of communication between machines to share the workload. Split the file into chunks of data (blobs), create a hash map to inform which peer owns what chunk. Distributed Hash Table. Kraken

Polling and Streaming

    Use cases: Chat, stock, temperature
    Polling — fetching the data with interval (API);
    Stream — create a connection that continuously retrieves (listening) the data (API) Web Socket

Configuration

    JSON or YAML
    Can be static — constant, pros constant hard to break system, cons — require rebuilding
    Dynamic (stored in KV — Redis) pros — flexible, fast to apply, cons — can break system easily if no test

Rate limiting — security, reliability

    Reduce spamming system, avoid DoS and DDoS (Distributed) Denial of Service attack;
    Implemented by the cache, hard to use server cache because Load Balancer will not know how to redirect;
    Use external key-value databases (Redis) in reverse proxy (Load Balancer);
    Rate limiting rules can be simple or more complex (base on tiers) depending on the system;

Logging — reliability, growing

    Logging — collecting info from events. All the events will log in STDOUT, STDERR. Save them to Time-series database: InfluxDB, Prometheus
    Monitor — visualizing with Grafana;
    Alert — If the monitoring system has a spike, create the alert, for example, post in slack;
    Logging is important during system growing

Pub/sub pattern — Stream

    Pub/Sub model;
    Use cases: chat messages, a news feed, stock prices, notification;
    Guarantees: at-least-one-delivery, persistent storage, ordering, replayability;
    Publisher Server (P) — Topic Channel (T) — Message — Subscriber (P) -> (T) — m1 — m2 -> (S)
    Idempotent operation — have the same result regardless of how many times it called;
    Apache Kafka — streaming system by LinkedIn, Google Cloud Sub/Pub guarantee at-least-one-delivery;

MapReduce — Scalability Big Data

    File System — abstraction of organizing data (hierarchy, tree — Unix file system). Distributed File System — the same but split data between machines — Google File System, Hadoop Distributed File System;
    MapReduce — distribute data: fast, efficient, and fail-tolerant; Dataset split between multiple machines -> (Map) for each chunk to key:val -> (Shuffle) reorganize -> (Reduce) transform to more meaningful data;
    Prerequisite — we have distributed file system, we have dataset split into chunks, divided between multiple machines, have central control (know what is going on and communicate with map/reduce workers); servers send mapped data;
    If a failure occurs, central control rerun map/reduce (idempotent operation)

Security

    Man in the middle attack — intercept and mutate private IP packet from client to server. Encryption and HTTPS protection;
    Symmetric Encryption — Use one key to encrypt and decrypt data. Faster than Asymmetric. Its algorithms are part of the Advanced Encryption Standard (AES);
    Advanced Encryption Standard — standard symmetric encryption algorithms (AES — 128, AES — 192, AES — 256);
    Asymmetric Encryption — public and private key to encrypt and decrypt. Data encrypted with a public key (can be shared), maybe only decrypted with a private key (need to be secure). Slower than symmetric;
    HTTPS — secure connection. Requires server to have SSL Certificate and uses TLS to communicate (encrypt data) between server-clients;
    TLS — transport layer security protocol built on top of TCP;
    SSL Certificate — server was granted a digital certificate by Certificate Authority. Contains servers public key. To establish TLS handshake in HTTPS connection;
    Certificate Authority — an entity that is verifying the certificate source of public key;
    TLS Handshake — establishing a secure connection between client and server. The client sends a string of random bytes (client hello) -> server sends another string of random bytes (server hello) + SSL Certificate with public key -> client verifies the certificate with Certificate Authority and sends premaster secret encrypted with a public key string of random bytes -> client and server use client hello, server hello and premaster secret to generate session key (using symmetric encryption), and encrypt all the data during communication;


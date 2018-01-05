## Search Engine

## Build

    mvn clean install
    
## Usage

After war deploy you will have admin panel where :
1. You can add documents in comma separated format (a,aa,aaa)

2.a You can search documents by tokens in comma separated format (a,aa,aaa)
2.b You can search documents by tokens in comma separated format (a,aa,aaa)

3. You can see all documents

P.s. To change document format and parse function add your Parser that implements IDocumentParser interface.
P.p.s. By default I'm using H2 in-memory DB.

## Authors
  * Pryimak Vasyl

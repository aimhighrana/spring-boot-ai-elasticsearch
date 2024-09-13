## Getting Started
### Spring boot - nextjs application for MDO Search ```Beta```

### How can i run locally ?
#### Prerequisite
1. Run elasticsearch8.3.5
2. Java 21 

#### Build and run
1. application.properties [If you want to customize props ]
2. mvn clean
3. mvn install
4. mvn spring-boot:start 

#### Access the application
Open ```http://localhost:9887/```


#### Production deployment 
1. mvn clean package 
2. docker build . -t <tagname>
3. Add the environment variable like below for actual elastic connection
```
spring.elastic.client.username=elastic
spring.elastic.client.password=password
spring.elastic.client.host=<elastic service path>
spring.elastic.client.port=9100

server.port=8080

search.index=dev.masterdataonline.com_elastic-fuzzy_latest-beta
```
4. For readiness prop check the below link
```
http://localhost:9887/actuator/health/readiness
```


### Below for developers only ....

First, run the development server:

```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `app/page.tsx`. The page auto-updates as you edit the file.

This project uses [`next/font`](https://nextjs.org/docs/basic-features/font-optimization) to automatically optimize and load Inter, a custom Google Font.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.
- [Learn Next.js](https://nextjs.org/learn) - an interactive Next.js tutorial.

You can check out [the Next.js GitHub repository](https://github.com/vercel/next.js/) - your feedback and contributions are welcome!

## Deploy on Vercel

The easiest way to deploy your Next.js app is to use the [Vercel Platform](https://vercel.com/new?utm_medium=default-template&filter=next.js&utm_source=create-next-app&utm_campaign=create-next-app-readme) from the creators of Next.js.

Check out our [Next.js deployment documentation](https://nextjs.org/docs/deployment) for more details.

# Install Apollo RoverCLI


# Install Apollo Router
curl -sSL https://router.apollo.dev/download/nix/latest | sh

rover dev --name airport --schema ~/Learn/graphql/graphql-tech-talk/subgraphs/airport-api/src/schema/schema.gql --url http://localhost:8081/graphql
rover dev --name crew --schema ~/Learn/graphql/graphql-tech-talk/src/main/resources/graphql/schema.graphqls --url http://localhost:8080/graphql

#command to generate super graph schema file
rover supergraph compose --config ./supergraph.yaml > supergraph-schema.graphql

#command to run the router using the above generated schema file
./router --dev --supergraph supergraph-schema.graphql
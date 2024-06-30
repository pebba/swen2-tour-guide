# create and start container
docker run -d --name tourapp -e POSTGRES_PASSWORD=pwd -e POSTGRES_USER=postgres -e POSTGRES_DB=tourdb -p 5433:5432 postgres:latest

Start-Sleep -Seconds 3

# create table
$sqlCommand = @"
CREATE TABLE IF NOT EXISTS tours (
    name VARCHAR(50) PRIMARY KEY,
    description VARCHAR(50),
    departure VARCHAR(50),
    destination VARCHAR(50),
    transport VARCHAR(50),
    distance FLOAT,
    time FLOAT
);
"@

# execute sql inside docker
docker exec -i tourapp psql -U postgres -d tourdb -c "$sqlCommand"

Write-Host "Database and table 'tours' created successfully."

CREATE DATABASE IF NOT EXISTS comercio;
CREATE DATABASE IF NOT EXISTS produto_branch;
CREATE DATABASE IF NOT EXISTS usuario;

GRANT ALL PRIVILEGES ON comercio.* TO 'microservice'@'%';
GRANT ALL PRIVILEGES ON produto_branch.* TO 'microservice'@'%';
GRANT ALL PRIVILEGES ON usuario.* TO 'microservice'@'%';

FLUSH PRIVILEGES;

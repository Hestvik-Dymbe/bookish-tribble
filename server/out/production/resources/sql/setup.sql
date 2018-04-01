DROP TABLE task;
DROP TABLE tribbler;

CREATE TABLE tribbler(
    tribblerId  SERIAL,
    name        VARCHAR(30) NOT NULL,
    PRIMARY KEY (tribblerId)
);

CREATE TABLE task(
    taskId      SERIAl,
    summary     VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    stake       NUMERIC NOT NULL,
    timestamp   INTEGER NOT NULL,
    deadline    INTEGER NOT NULL,
    completed   BOOLEAN NOT NULL,
    opponentId  INTEGER NOT NULL,
    tribblerId  INTEGER NOT NULL,
    PRIMARY KEY (taskId),
    FOREIGN KEY (tribblerId) REFERENCES tribbler(tribblerId)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

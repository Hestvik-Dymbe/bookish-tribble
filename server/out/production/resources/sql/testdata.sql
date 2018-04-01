INSERT INTO tribbler(name) VALUES
    ('Erik Dymbe'),
    ('Sander Hestvik'),
    ('Bendik Brunvoll'),
    ('Jenny Manne'),
    ('Thomas Haglund');

INSERT INTO task(summary, description, stake, timestamp, deadline, completed, opponentId, tribblerId) VALUES
    ('Jobbe', 'Skal begynne å jobbe med statistikk før kl. 19:00', 50, 1522061491, 1522062491, TRUE, 2, 1),
    ('Gjøre ferdig server', 'Tribble sin server skal kunne svare på enkle http-forespørsler fra appen', 100, 1522070491, 1522071491, FALSE, 3, 1),
    ('Stå på ski', 'Skal stå minst en gang på ski denne påsken', 100, 1522070491, 1522071491, FALSE, 2, 1),
    ('Gjøre MMI', 'Skal gjøre', 100, 1522070491, 1522071491, FALSE, 3, 4),
    ('Gjøre ferdig appen', 'Tribble skal være ferdig før påsken er ferdig', 150, 1522071491, 1522072491, FALSE, 1, 2);

SELECT * FROM tribbler JOIN task ON tribbler.tribblerId = task.tribblerId;
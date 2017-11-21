delete from conference;
insert into conference(id, name, description, begins, ends, city) values (1, 'TestConference', 'TestConference!', parsedatetime('19-06-2020','dd-mm-yyyy'), parsedatetime('21-06-2020','dd-mm-yyyy'), 'Amersfoort');

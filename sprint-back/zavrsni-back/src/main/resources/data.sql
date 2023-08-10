INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

           
INSERT INTO stanje (ime) VALUES ('Nov');
INSERT INTO stanje (ime) VALUES ('U toku');
INSERT INTO stanje (ime) VALUES ('Zavrsen');

INSERT INTO sprint (ime, ukupno_bodova) VALUES ('Sprint 1', '9');
INSERT INTO sprint (ime, ukupno_bodova) VALUES ('Sprint 2', '13');

INSERT INTO zadatak (ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES ('Zadatak 1', 'Milan', '5', 2, 1);
INSERT INTO zadatak (ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES ('Zadatak 2', 'Goran', '6', 1, 3);
INSERT INTO zadatak (ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES ('Zadatak 3', 'Dejan', '3', 1, 2);
INSERT INTO zadatak (ime, zaduzeni, bodovi, sprint_id, stanje_id) VALUES ('Zadatak 4', 'Dragana', '8', 2, 1);
CREATE TABLE if NOT EXISTS users(
  user_id int IDENTITY (1,1) PRIMARY KEY,
  parent_id int not null,
  reminder_id int not null,
  ticket_id int not null,
  constraint user_parent_fk FOREIGN KEY(parent_id) REFERENCES parents(parent_id) ON DELETE CASCADE,
  constraint user_reminder_fk FOREIGN KEY(reminder_id) REFERENCES reminders(reminder_id) ON DELETE CASCADE,
  constraint user_tiket_fk FOREIGN KEY(ticket_id) REFERENCES tickets(ticket_id) ON DELETE CASCADE,
  userName nvarchar(50) NOT NULL,
  firstName nvarchar(25) NOT NULL,
  lastName nvarchar(25) NOT NULL,
  email nvarchar(50) NOT NULL,
  belt nvarchar(10) NULL,
  stripe NUMERIC(1,0) NULL,
  tel int NULL,
  bornDate DATE NOT NULL,
  role nvarchar(25) NOT NULL
);
CREATE UNIQUE INDEX ix_username on users(username, lastname);

create table IF NOT EXISTS authorities (
  authorities_id int IDENTITY(1,1) NOT NULL,
  constraint fk_authorities_users foreign key(user_id) references users(user_id) on delete cascade,
  username nvarchar(50) NOT NULL,
  authority nvarchar(50) NOT NULL
);
create unique index ix_auth_username on authorities(username,authority);

CREATE TABLE  IF NOT EXISTS attendance(
  attendace_id int IDENTITY (1,1) PRIMARY KEY,
  user_id int not null,
  training_id int not null,
  constraint att_train_fk FOREIGN KEY(training_id) REFERENCES trainings(training_id) ON DELETE CASCADE,
  constraint att_user_fk FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  trainingDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS parents(
  parent_id int IDENTITY (1,1) PRIMARY KEY,
  user_id int not null,
  constraint par_user_fk FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  firstName nvarchar(25) NOT NULL,
  lastName nvarchar(25) NOT NULL,
  email nvarchar(50) NOT NULL,
  agreement bit NOT NULL,
  tel int NULL
);
CREATE UNIQUE INDEX ix_parents ON parents(lastname);

CREATE TABLE IF NOT EXISTS reminders(
  reminder_id int IDENTITY (1,1) PRIMARY KEY,
  reminderCount int NOT NULL,
  startingDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tickets(
  ticket_id int IDENTITY (1,1) PRIMARY KEY,
  entry int NOT NULL,
  endingDate DATE NOT NULL,
  startingDate DATE NOT NULL,
  isTimeTicket BIT NOT NULL
);

CREATE TABLE IF NOT EXISTS trainings(
  training_id int IDENTITY (1,1) PRIMARY KEY,
  title nvarchar(50) NOT NULL,
  beginning DATE NOT NULL,
  ending DATE NOT NULL,
  trainingLevel int NULL,
  coach nvarchar(50) null,
  capacity int NULL,
  note nvarchar(500) NULL
);
CREATE UNIQUE INDEX ix_trainig ON trainings(title);
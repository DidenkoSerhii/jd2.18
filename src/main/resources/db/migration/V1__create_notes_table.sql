CREATE TABLE notes (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL
);

CREATE INDEX idx_notes_title ON notes (title);
CREATE INDEX idx_notes_content ON notes (content);
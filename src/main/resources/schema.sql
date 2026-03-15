CREATE TABLE IF NOT EXISTS vectors (
                                       id text PRIMARY KEY ,
                                       vector JSONB,
                                       metadata JSONB
)
DETACHED?=$(DETACHED)

docker-full:
	@docker-compose -f docker-compose.yaml -f docker-compose.dev-tools.yaml up \
		$(if DETACHED, -d,)

docker:
	@docker-compose up -d

docker-down:
	@docker-compose down --remove-orphans

postgre-seed:
	docker-compose exec -d postgres psql -f /docker-entrypoint-initdb.d/database-seed.sql

clean:
	@docker volume rm monorepo_pgsql

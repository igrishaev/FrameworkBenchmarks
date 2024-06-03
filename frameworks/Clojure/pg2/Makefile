
PROJECT = pg2
ROOT = ../../..
TAG = tfbpg2

docker-build:
	docker build --no-cache -t ${TAG} -f ${PROJECT}.dockerfile .

docker-run:
	docker run -it --rm -p 8080:8080 ${TAG}

verify-db:
	cd ${ROOT} && ./tfb --mode verify --test ${PROJECT} --type db

bench-db:
	cd ${ROOT} && ./tfb --mode benchmark --test ${PROJECT} --type db

bench-db-http-kit:
	cd ${ROOT} && ./tfb --mode benchmark --test http-kit --type db

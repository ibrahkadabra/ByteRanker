FROM node:20-alpine
WORKDIR /app
COPY apps/web /app
RUN npm i -g pnpm && pnpm i && pnpm build
EXPOSE 5173
CMD ["pnpm","preview","--host","0.0.0.0","--port","5173"]

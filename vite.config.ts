import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: 'src/main/resources/static',
    emptyOutDir: true,
  },
  server: {
    proxy: {
      '/textdata': 'http://localhost:8080'
    }
  }
})

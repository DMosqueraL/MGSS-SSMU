import type { Config } from 'tailwindcss'

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      backgroundImage: {
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        'gradient-conic':
          'conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))',
      },
    
      
      colors:{
        
        'green-socio': '#ABD08D',
        'peach-socio': '#FBCB9E',
        'red-socio': '#F39A9E',
        'gray-socio': '#D6D6D6',
        'blue-socio': '#6662D9',
        'lightBlue-socio': '#D7D5EC',
        'light-socio': '#EFEFF8',
      }
    },
  },
  plugins: [],
}
export default config

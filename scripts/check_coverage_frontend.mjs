import fs from 'fs';
const file = process.argv[2] || 'coverage/coverage-summary.json';
const min = Number(process.argv[3] || 80);
const data = JSON.parse(fs.readFileSync(file, 'utf-8'));
const pct = data.total.lines.pct;
if (pct < min) {
  console.error(`Coverage check failed: lines ${pct}% < ${min}%`);
  process.exit(1);
} else {
  console.log(`Coverage OK: ${pct}% ≥ ${min}%`);
}

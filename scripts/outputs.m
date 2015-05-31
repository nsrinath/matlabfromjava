function [m,s] = outputs(x)
  n = length(x);
  m = sum(x)/n;
  s = sqrt(sum((x-m).^2/n));
  display(m);
  display(s);
end

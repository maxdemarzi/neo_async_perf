require 'bundler'
Bundler.require
require 'csv'

def create_csv
  properties = ["userid", "url"]
  File.open("requests.csv", "w") do |file|
    file.puts properties.join(",")
  end
  
  requests = File.open("requests.csv", "a")
  5000.times do |t|
    user = rand.to_s[2..8]
    rand(1..10).times do
      requests.puts [user, Faker::Internet.http_url].join(",")
    end
    
    site = Faker::Internet.http_url
    rand(1..10).times do
      requests.puts [rand.to_s[2..8], site].join(",")
    end
    
  end  
  requests.close
end

create_csv
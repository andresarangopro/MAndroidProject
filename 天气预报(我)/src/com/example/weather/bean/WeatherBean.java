package com.example.weather.bean;

import java.util.List;

public class WeatherBean {
	String reason;
	Result result;
	int error_code;
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return "WeatherBean [reason=" + reason + ", result=" + result
				+ ", error_code=" + error_code + "]";
	}

	public static class Result{
		Data data;
		public Data getData() {
			return data;
		}
		public void setData(Data data) {
			this.data = data;
		}
		@Override
		public String toString() {
			return "Result [data=" + data + "]";
		}
		public static class Data{
			RealTime realtime;
			Life life;
			List<Weather> weather;
			PM25 pm25;
			int isForeign;
			String date;
			public RealTime getRealtime() {
				return realtime;
			}
			public void setRealtime(RealTime realtime) {
				this.realtime = realtime;
			}
			public Life getLife() {
				return life;
			}
			public void setLife(Life life) {
				this.life = life;
			}
			public List<Weather> getWeather() {
				return weather;
			}
			public void setWeather(List<Weather> weather) {
				this.weather = weather;
			}
			public PM25 getPm25() {
				return pm25;
			}
			public void setPm25(PM25 pm25) {
				this.pm25 = pm25;
			}
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
			public int getIsForeign() {
				return isForeign;
			}
			public void setIsForeign(int isForeign) {
				this.isForeign = isForeign;
			}
			@Override
			public String toString() {
				return "Data [realtime=" + realtime + ", life=" + life
						+ ", weather=" + weather + ", pm25=" + pm25
						+ ", isForeign=" + isForeign + ", date=" + date + "]";
			}
			public static class RealTime{
				Wind wind;
				String time;
				Weather weather;
				long dataUptime;
				String date;
				String city_name;
				String city_code;
				int week;
				String moon;
				public Wind getWind() {
					return wind;
				}
				public void setWind(Wind wind) {
					this.wind = wind;
				}
				public String getTime() {
					return time;
				}
				public void setTime(String time) {
					this.time = time;
				}
				public Weather getWeather() {
					return weather;
				}
				public void setWeather(Weather weather) {
					this.weather = weather;
				}
				public long getDataUptime() {
					return dataUptime;
				}
				public void setDataUptime(long dataUptime) {
					this.dataUptime = dataUptime;
				}
				public String getDate() {
					return date;
				}
				public void setDate(String date) {
					this.date = date;
				}
				public String getCity_name() {
					return city_name;
				}
				public void setCity_name(String city_name) {
					this.city_name = city_name;
				}
				public String getCity_code() {
					return city_code;
				}
				public void setCity_code(String city_code) {
					this.city_code = city_code;
				}
				public int getWeek() {
					return week;
				}
				public void setWeek(int week) {
					this.week = week;
				}
				public String getMoon() {
					return moon;
				}
				public void setMoon(String moon) {
					this.moon = moon;
				}
				@Override
				public String toString() {
					return "RealTime [wind=" + wind + ", time=" + time
							+ ", weather=" + weather + ", dataUptime="
							+ dataUptime + ", date=" + date + ", city_name="
							+ city_name + ", city_code=" + city_code
							+ ", week=" + week + ", moon=" + moon + "]";
				}
				public static class Wind{
					String windspeed;
					String direct;
					String power;
					String offset;
					@Override
					public String toString() {
						return "Wind [windspeed=" + windspeed + ", direct="
								+ direct + ", power=" + power + ", offset="
								+ offset + "]";
					}
					public String getWindspeed() {
						return windspeed;
					}
					public void setWindspeed(String windspeed) {
						this.windspeed = windspeed;
					}
					public String getDirect() {
						return direct;
					}
					public void setDirect(String direct) {
						this.direct = direct;
					}
					public String getPower() {
						return power;
					}
					public void setPower(String power) {
						this.power = power;
					}
					public String getOffset() {
						return offset;
					}
					public void setOffset(String offset) {
						this.offset = offset;
					}
				}
				public static class Weather{
					String humidity;
					String img;
					String info;
					String temperature;
					public String getHumidity() {
						return humidity;
					}
					public void setHumidity(String humidity) {
						this.humidity = humidity;
					}
					public String getImg() {
						return img;
					}
					public void setImg(String img) {
						this.img = img;
					}
					public String getInfo() {
						return info;
					}
					public void setInfo(String info) {
						this.info = info;
					}
					public String getTemperature() {
						return temperature;
					}
					public void setTemperature(String temperature) {
						this.temperature = temperature;
					}
					@Override
					public String toString() {
						return "Weather [humidity=" + humidity + ", img="
								+ img + ", info=" + info + ", temperature="
								+ temperature + "]";
					}
				}
			}
			public static class Life{
				String date;
				Info info;
				public String getDate() {
					return date;
				}
				public void setDate(String date) {
					this.date = date;
				}
				public Info getInfo() {
					return info;
				}
				public void setInfo(Info info) {
					this.info = info;
				}
				@Override
				public String toString() {
					return "Life [date=" + date + ", info=" + info + "]";
				}
				public static class Info{
					List<String > kongtiao;
					List<String> yundong;
					List<String> ziwaixian;
					List<String> ganmo;
					List<String> xiche;
					List<String> wuran;
					List<String> chuanyi;
					public List<String> getKongtiao() {
						return kongtiao;
					}
					public void setKongtiao(List<String> kongtiao) {
						this.kongtiao = kongtiao;
					}
					public List<String> getYundong() {
						return yundong;
					}
					public void setYundong(List<String> yundong) {
						this.yundong = yundong;
					}
					public List<String> getZiwaixian() {
						return ziwaixian;
					}
					public void setZiwaixian(List<String> ziwaixian) {
						this.ziwaixian = ziwaixian;
					}
					public List<String> getGanmo() {
						return ganmo;
					}
					public void setGanmo(List<String> ganmo) {
						this.ganmo = ganmo;
					}
					public List<String> getXiche() {
						return xiche;
					}
					public void setXiche(List<String> xiche) {
						this.xiche = xiche;
					}
					public List<String> getWuran() {
						return wuran;
					}
					public void setWuran(List<String> wuran) {
						this.wuran = wuran;
					}
					public List<String> getChuanyi() {
						return chuanyi;
					}
					public void setChuanyi(List<String> chuanyi) {
						this.chuanyi = chuanyi;
					}
					@Override
					public String toString() {
						return "Info [kongtiao=" + kongtiao + ", yundong="
								+ yundong + ", ziwaixian=" + ziwaixian
								+ ", ganmo=" + ganmo + ", xiche=" + xiche
								+ ", wuran=" + wuran + ", chuanyi=" + chuanyi
								+ "]";
					}
				}
			}
			public static class Weather{
				String date;
				Info info;
				public String getDate() {
					return date;
				}
				public void setDate(String date) {
					this.date = date;
				}
				public Info getInfo() {
					return info;
				}
				public void setInfo(Info info) {
					this.info = info;
				}
				@Override
				public String toString() {
					return "Weather [date=" + date + ", info=" + info + "]";
				}
				public static class Info{
					List<String> night;
					List<String> day;
					List<String> dawn;
					public List<String> getNight() {
						return night;
					}
					public void setNight(List<String> night) {
						this.night = night;
					}
					public List<String> getDay() {
						return day;
					}
					public void setDay(List<String> day) {
						this.day = day;
					}
					public List<String> getDawn() {
						return dawn;
					}
					public void setDawn(List<String> dawn) {
						this.dawn = dawn;
					}
					@Override
					public String toString() {
						return "Info [night=" + night + ", day=" + day
								+ ", dawn=" + dawn + "]";
					}
				}
			}
			public static class PM25{
				String key ;
				int show_desc;
				Pm25 pm25;
				String dateTime;
				String cityName;
				public String getKey() {
					return key;
				}
				public void setKey(String key) {
					this.key = key;
				}
				public int getShow_desc() {
					return show_desc;
				}
				public void setShow_desc(int show_desc) {
					this.show_desc = show_desc;
				}
				public Pm25 getPm25() {
					return pm25;
				}
				public void setPm25(Pm25 pm25) {
					this.pm25 = pm25;
				}
				public String getDateTime() {
					return dateTime;
				}
				public void setDateTime(String dateTime) {
					this.dateTime = dateTime;
				}
				public String getCityName() {
					return cityName;
				}
				public void setCityName(String cityName) {
					this.cityName = cityName;
				}
				@Override
				public String toString() {
					return "PM25 [key=" + key + ", show_desc=" + show_desc
							+ ", pm25=" + pm25 + ", dateTime=" + dateTime
							+ ", cityName=" + cityName + "]";
				}
				public static class Pm25{
					String curPm;
					String pm25;
					String pm10;
					int level;
					String quality;
					String des;
					public String getCurPm() {
						return curPm;
					}
					public void setCurPm(String curPm) {
						this.curPm = curPm;
					}
					public String getPm25() {
						return pm25;
					}
					public void setPm25(String pm25) {
						this.pm25 = pm25;
					}
					public String getPm10() {
						return pm10;
					}
					public void setPm10(String pm10) {
						this.pm10 = pm10;
					}
					public int getLevel() {
						return level;
					}
					public void setLevel(int level) {
						this.level = level;
					}
					public String getQuality() {
						return quality;
					}
					public void setQuality(String quality) {
						this.quality = quality;
					}
					public String getDes() {
						return des;
					}
					public void setDes(String des) {
						this.des = des;
					}
					@Override
					public String toString() {
						return "Pm25 [curPm=" + curPm + ", pm25=" + pm25
								+ ", pm10=" + pm10 + ", level=" + level
								+ ", quality=" + quality + ", des=" + des + "]";
					}
				}
			}
		}
	}
}

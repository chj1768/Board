package chj.com.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import chj.com.myapp.dto.Dto;

public class Dao {
	
	DataSource dataSource;
	
	public Dao() {
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mariadb");
		} catch( NamingException e ) {
			e.printStackTrace();	
		}
		
	}
	
	public Dto content( String bId ) {
			
			uphit( bId );
			Dto dto = null;
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = dataSource.getConnection();
				int idx = Integer.parseInt(bId);
				String query = "select * from board where bId = ?";
				preparedStatement = connection.prepareStatement( query );
				preparedStatement.setInt( 1, idx );
				resultSet = preparedStatement.executeQuery();
	
				while(resultSet.next()) {
					String bName = resultSet.getString("bName");
					String bTitle = resultSet.getString("bTitle");
					String bContent = resultSet.getString("bContent");
					Timestamp bDate = resultSet.getTimestamp("bDate");
					int bHit = resultSet.getInt("bHit");
					int bGroup = resultSet.getInt("bGroup");
					int bStep = resultSet.getInt("bStep");
					int bIndent = resultSet.getInt("bIndent");
					dto = new Dto( idx, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent );
				}
				
			} catch( Exception e ) {
				e.printStackTrace();
				
			} finally {
				try {
					if( resultSet != null ) {
						resultSet.close();
					}
					if ( preparedStatement != null ) {
						preparedStatement.close();
					}
					if ( connection != null ) {
						connection.close();
					}
					
				} catch( Exception e2 ) {
					e2.printStackTrace();
				}
			
			}
			
			return dto;
			
	}
	
	public void uphit( String bId ) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = dataSource.getConnection();
			int idx = Integer.parseInt(bId);
			String query = "update board set bHit = bHit + 1 where bId = ?";
			preparedStatement = connection.prepareStatement( query );
			preparedStatement.setInt( 1, idx );
			preparedStatement.executeQuery();

		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
				if ( resultSet != null ) { resultSet.close();}
				if ( preparedStatement != null ) { preparedStatement.close();}
				if ( connection != null ) { connection.close();}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
		
	}
	
	public void modify( String bId, String bName, String bTitle, String bContent ) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "update board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			preparedStatement = connection.prepareStatement( query );
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId) );
			preparedStatement.executeQuery();

		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
				if ( resultSet != null ) { resultSet.close();}
				if ( preparedStatement != null ) { preparedStatement.close();}
				if ( connection != null ) { connection.close();}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
		
	}
	
	public void delete( String bId ) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println(bId);
		try {
			connection = dataSource.getConnection();
			String query = "delete from board where bId = ?";
			preparedStatement = connection.prepareStatement( query );
			preparedStatement.setInt(1, Integer.parseInt(bId) );
			ResultSet k = preparedStatement.executeQuery();
			System.out.println(k);

		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
				if ( resultSet != null ) { resultSet.close();}
				if ( preparedStatement != null ) { preparedStatement.close();}
				if ( connection != null ) { connection.close();}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
		
		
	}

	public void write( String bName, String bTitle, String bContent ) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			connection = dataSource.getConnection();
			String query = "select max(bId) from board";
			int idx = 0;
			preparedStatement = connection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				  idx = resultSet.getInt(1);
			}
			query = "insert into board ( bName, bTitle, bContent, bHit, bGroup, bStep, bIndent ) "
					+ "values ( ?,?,?,0,?,0, 0 )";
			preparedStatement = connection.prepareStatement( query );
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, idx + 1 );
			preparedStatement.executeQuery();

		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
				if ( resultSet != null ) { resultSet.close();}
				if ( preparedStatement != null ) { preparedStatement.close();}
				if ( connection != null ) { connection.close();}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
			
	} 

	public ArrayList<Dto> list() {
		
		ArrayList<Dto> dtos = new ArrayList<Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select bId, bName, bTitle, bDate, bContent, bHit, bGroup, bStep, bIndent from board order by bGroup desc, bStep asc";
			preparedStatement = connection.prepareStatement( query );
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				Dto dto = new Dto( bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent );
				dtos.add(dto);
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
				if( resultSet != null ) {
					resultSet.close();
				}
				if ( preparedStatement != null ) {
					preparedStatement.close();
				}
				if ( connection != null ) {
					connection.close();
				}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
		
		return dtos;
		
	}
	
	public Dto reply_view(String bId) {
		
		Dto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			int idx = Integer.parseInt(bId);
			String query = "select * from board where bId = ?";
			preparedStatement = connection.prepareStatement( query );
			preparedStatement.setInt( 1, idx );
			resultSet = preparedStatement.executeQuery();

			if ( resultSet.next() ) {
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				dto = new Dto( idx, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent );
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
				if( resultSet != null ) {
					resultSet.close();
				}
				if ( preparedStatement != null ) {
					preparedStatement.close();
				}
				if ( connection != null ) {
					connection.close();
				}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
		
		return dto;
		
	}
	
	public void replyShape(String strGroup, String strStep ) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "update board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			preparedStatement.setInt(1, Integer.parseInt( strGroup ));
			preparedStatement.setInt(2, Integer.parseInt( strStep ));
			preparedStatement.executeQuery();
			
		} catch( Exception e ) {
			e.printStackTrace();
			
		} finally {
			try {
			
				if ( preparedStatement != null ) {
					preparedStatement.close();
				}
				if ( connection != null ) {
					connection.close();
				}
				
			} catch( Exception e2 ) {
				e2.printStackTrace();
			}
		
		}
		
		
	}
	
	public void reply( String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent ) {
			
			replyShape(bGroup, bStep);
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			try {
				connection = dataSource.getConnection();
				String query = "insert into board(bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (?,?,?,0,?,?,? )";
				preparedStatement = connection.prepareStatement( query );
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				preparedStatement.setInt(4, Integer.parseInt( bGroup ));
				preparedStatement.setInt(5, Integer.parseInt( bStep ) + 1);
				preparedStatement.setInt(6, Integer.parseInt( bIndent ) + 1);
				preparedStatement.executeQuery();
				
			} catch( Exception e ) {
				e.printStackTrace();
				
			} finally {
				try {
					
					if ( preparedStatement != null ) {
						preparedStatement.close();
					}
					if ( connection != null ) {
						connection.close();
					}
					
				} catch( Exception e2 ) {
					e2.printStackTrace();
				}
			
			}
			
			
		}

	
}
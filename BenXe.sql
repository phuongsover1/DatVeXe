USE [master]
GO
/****** Object:  Database [BenXe]    Script Date: 5/3/2022 5:12:11 PM ******/
CREATE DATABASE [BenXe]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BenXe', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLSERVER\MSSQL\DATA\BenXe.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BenXe_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLSERVER\MSSQL\DATA\BenXe_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [BenXe] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BenXe].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BenXe] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BenXe] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BenXe] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BenXe] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BenXe] SET ARITHABORT OFF 
GO
ALTER DATABASE [BenXe] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BenXe] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BenXe] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BenXe] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BenXe] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BenXe] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BenXe] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BenXe] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BenXe] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BenXe] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BenXe] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BenXe] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BenXe] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BenXe] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BenXe] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BenXe] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BenXe] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BenXe] SET RECOVERY FULL 
GO
ALTER DATABASE [BenXe] SET  MULTI_USER 
GO
ALTER DATABASE [BenXe] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BenXe] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BenXe] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BenXe] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BenXe] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BenXe] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BenXe', N'ON'
GO
ALTER DATABASE [BenXe] SET QUERY_STORE = OFF
GO
USE [BenXe]
GO
/****** Object:  Table [dbo].[authorities]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[authorities](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](11) NULL,
	[authority] [nvarchar](50) NULL,
 CONSTRAINT [PK_authorities] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Chuyen_Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chuyen_Xe](
	[ma_chuyen] [int] IDENTITY(1,1) NOT NULL,
	[ma_xe] [int] NULL,
	[ma_tuyen] [int] NULL,
	[gio_chay] [smalldatetime] NULL,
	[da_hoan_thanh] [smallint] NOT NULL,
 CONSTRAINT [PK_Chuyen_Xe] PRIMARY KEY CLUSTERED 
(
	[ma_chuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_Ve_Ghe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_Ve_Ghe](
	[id_ve] [int] NOT NULL,
	[id_ghe] [nchar](3) NOT NULL,
 CONSTRAINT [PK_CTVeGhe] PRIMARY KEY CLUSTERED 
(
	[id_ve] ASC,
	[id_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CT_Xe_Ghe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_Xe_Ghe](
	[id_xe] [int] NOT NULL,
	[ma_ghe] [nchar](3) NOT NULL,
 CONSTRAINT [PK_CTXeGhe] PRIMARY KEY CLUSTERED 
(
	[id_xe] ASC,
	[ma_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dia_Diem]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dia_Diem](
	[id_dia_diem] [int] IDENTITY(1,1) NOT NULL,
	[ten_dia_diem] [nvarchar](50) NULL,
 CONSTRAINT [PK_Dia_Diem] PRIMARY KEY CLUSTERED 
(
	[id_dia_diem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ghe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ghe](
	[ma_ghe] [nchar](3) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ma_ghe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Khach_Hang]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Khach_Hang](
	[id_khach_hang] [int] IDENTITY(1,1) NOT NULL,
	[ho_ten] [nvarchar](50) NULL,
	[so_dien_thoai] [nvarchar](11) NULL,
	[email] [nvarchar](50) NULL,
	[cmnd] [nvarchar](12) NULL,
	[dia_chi] [nvarchar](300) NULL,
	[gioi_tinh] [nvarchar](3) NULL,
	[ngay_sinh] [date] NULL,
	[nghe_nghiep] [nvarchar](30) NULL,
	[id_tai_khoan] [int] NULL,
 CONSTRAINT [PK__Khach_Ha__9B2CAEF3FC40634C] PRIMARY KEY CLUSTERED 
(
	[id_khach_hang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Loai_Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Loai_Xe](
	[id_loai] [int] IDENTITY(1,1) NOT NULL,
	[ten_loai] [nvarchar](20) NULL,
	[so_cho] [int] NULL,
	[tien_ve_moi_cho] [money] NULL,
 CONSTRAINT [PK_Loai_Xe] PRIMARY KEY CLUSTERED 
(
	[id_loai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Nhan_Vien]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Nhan_Vien](
	[id_nhan_vien] [int] IDENTITY(1,1) NOT NULL,
	[so_dien_thoai] [nvarchar](11) NULL,
	[id_tai_khoan] [int] NULL,
	[ho_ten] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[cmnd] [nvarchar](12) NULL,
	[gioi_tinh] [nvarchar](3) NULL,
	[ngay_sinh] [date] NULL,
	[ngay_bd_lam_viec] [date] NULL,
	[da_nghi_viec] [int] NULL,
 CONSTRAINT [PK_Nhan_Vien] PRIMARY KEY CLUSTERED 
(
	[id_nhan_vien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tuyen_Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tuyen_Xe](
	[id_tuyen] [int] IDENTITY(1,1) NOT NULL,
	[dia_diem_di] [int] NULL,
	[dia_diem_den] [int] NULL,
	[dia_diem_len_xe] [nvarchar](50) NULL,
	[dia_diem_xuong_xe] [nvarchar](50) NULL,
	[thoi_gian_ton] [int] NULL,
	[so_km] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_tuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[user_id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](11) NULL,
	[password] [nvarchar](50) NULL,
	[enabled] [tinyint] NULL,
	[id_role] [int] NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ve_Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ve_Xe](
	[id_ve] [int] IDENTITY(1,1) NOT NULL,
	[id_khach_hang] [int] NULL,
	[id_nhan_vien_thanh_toan] [int] NULL,
	[id_chuyen_xe] [int] NULL,
	[ngay_lap] [smalldatetime] NULL,
	[trang_thai] [nvarchar](30) NULL,
	[tong_tien] [money] NULL,
	[hinh_thuc_thanh_toan] [nvarchar](20) NULL,
 CONSTRAINT [PK__Ve_Xe__014870B43C945F40] PRIMARY KEY CLUSTERED 
(
	[id_ve] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Xe](
	[id_xe] [int] IDENTITY(1,1) NOT NULL,
	[bien_so_xe] [nvarchar](20) NULL,
	[ma_loai_xe] [int] NULL,
 CONSTRAINT [PK_Xe] PRIMARY KEY CLUSTERED 
(
	[id_xe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[authorities] ON 

INSERT [dbo].[authorities] ([id], [username], [authority]) VALUES (9, N'0101010101', N'ROLE_USER')
INSERT [dbo].[authorities] ([id], [username], [authority]) VALUES (3, N'01214811211', N'ROLE_USER')
INSERT [dbo].[authorities] ([id], [username], [authority]) VALUES (8, N'0123456789', N'ROLE_EMPLOYEE')
INSERT [dbo].[authorities] ([id], [username], [authority]) VALUES (6, N'0903414287', N'ROLE_USER')
INSERT [dbo].[authorities] ([id], [username], [authority]) VALUES (5, N'0903414289', N'ROLE_USER')
SET IDENTITY_INSERT [dbo].[authorities] OFF
GO
SET IDENTITY_INSERT [dbo].[Chuyen_Xe] ON 

INSERT [dbo].[Chuyen_Xe] ([ma_chuyen], [ma_xe], [ma_tuyen], [gio_chay], [da_hoan_thanh]) VALUES (3, 2, 1, CAST(N'2022-03-10T08:00:00' AS SmallDateTime), 1)
INSERT [dbo].[Chuyen_Xe] ([ma_chuyen], [ma_xe], [ma_tuyen], [gio_chay], [da_hoan_thanh]) VALUES (4, 1, 1, CAST(N'2022-03-10T07:30:00' AS SmallDateTime), 1)
INSERT [dbo].[Chuyen_Xe] ([ma_chuyen], [ma_xe], [ma_tuyen], [gio_chay], [da_hoan_thanh]) VALUES (5, 3, 2, CAST(N'2022-03-13T12:30:00' AS SmallDateTime), 1)
INSERT [dbo].[Chuyen_Xe] ([ma_chuyen], [ma_xe], [ma_tuyen], [gio_chay], [da_hoan_thanh]) VALUES (6, 8, 4, CAST(N'2022-04-06T12:30:00' AS SmallDateTime), 0)
INSERT [dbo].[Chuyen_Xe] ([ma_chuyen], [ma_xe], [ma_tuyen], [gio_chay], [da_hoan_thanh]) VALUES (7, 2, 4, CAST(N'2022-04-08T12:50:00' AS SmallDateTime), 0)
INSERT [dbo].[Chuyen_Xe] ([ma_chuyen], [ma_xe], [ma_tuyen], [gio_chay], [da_hoan_thanh]) VALUES (8, 1, 3, CAST(N'2022-04-08T15:50:00' AS SmallDateTime), 0)
SET IDENTITY_INSERT [dbo].[Chuyen_Xe] OFF
GO
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (46, N'A02')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (47, N'A03')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (48, N'B03')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (49, N'A01')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (49, N'A02')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (50, N'A01')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (51, N'A11')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (52, N'A02')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (53, N'A08')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (54, N'A03')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (54, N'A06')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (54, N'A09')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (55, N'A01')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (55, N'A05')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (56, N'A05')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (58, N'A05')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (59, N'A04')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (1055, N'A07')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (1055, N'A08')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (1056, N'A02')
INSERT [dbo].[CT_Ve_Ghe] ([id_ve], [id_ghe]) VALUES (1056, N'A03')
GO
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'A12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (1, N'B12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A13')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A14')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A15')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A16')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A17')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'A18')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B13')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B14')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B15')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B16')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B17')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (2, N'B18')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'A12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (3, N'B12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'A12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B04')
GO
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (5, N'B12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A13')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A14')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A15')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A16')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A17')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'A18')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B01')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B02')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B03')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B04')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B05')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B06')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B07')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B08')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B09')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B10')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B11')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B12')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B13')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B14')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B15')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B16')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B17')
INSERT [dbo].[CT_Xe_Ghe] ([id_xe], [ma_ghe]) VALUES (8, N'B18')
GO
SET IDENTITY_INSERT [dbo].[Dia_Diem] ON 

INSERT [dbo].[Dia_Diem] ([id_dia_diem], [ten_dia_diem]) VALUES (2, N'Đà Lạt')
INSERT [dbo].[Dia_Diem] ([id_dia_diem], [ten_dia_diem]) VALUES (3, N'Đà Nẵng')
INSERT [dbo].[Dia_Diem] ([id_dia_diem], [ten_dia_diem]) VALUES (1, N'TP.Hồ Chí Minh')
SET IDENTITY_INSERT [dbo].[Dia_Diem] OFF
GO
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A01')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A02')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A03')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A04')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A05')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A06')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A07')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A08')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A09')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A10')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A11')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A12')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A13')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A14')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A15')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A16')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A17')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'A18')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B01')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B02')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B03')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B04')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B05')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B06')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B07')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B08')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B09')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B10')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B11')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B12')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B13')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B14')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B15')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B16')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B17')
INSERT [dbo].[Ghe] ([ma_ghe]) VALUES (N'B18')
GO
SET IDENTITY_INSERT [dbo].[Khach_Hang] ON 

INSERT [dbo].[Khach_Hang] ([id_khach_hang], [ho_ten], [so_dien_thoai], [email], [cmnd], [dia_chi], [gioi_tinh], [ngay_sinh], [nghe_nghiep], [id_tai_khoan]) VALUES (4, N'Lê Nguyễn Duy Phương', N'01214811211', N'phuongsover2@gmail.com', N'261619949', N'Liên Hương', N'Nam', CAST(N'2001-05-15' AS Date), N'Sinh Viên', 1)
INSERT [dbo].[Khach_Hang] ([id_khach_hang], [ho_ten], [so_dien_thoai], [email], [cmnd], [dia_chi], [gioi_tinh], [ngay_sinh], [nghe_nghiep], [id_tai_khoan]) VALUES (6, N'Đặng Anh Quân', N'0903414289', N'quan@gmail.com', N'1313131313', N'Bình Dương', N'Nam', CAST(N'2001-05-15' AS Date), N'Gamer', 3)
INSERT [dbo].[Khach_Hang] ([id_khach_hang], [ho_ten], [so_dien_thoai], [email], [cmnd], [dia_chi], [gioi_tinh], [ngay_sinh], [nghe_nghiep], [id_tai_khoan]) VALUES (7, N'Lê Nguyễn Yến Nhi', N'0903414287', N'nhi@gmail.com', N'1212121212', N'46, đường 17/4', N'Nữ', CAST(N'2003-10-16' AS Date), N'Học Sinh', 4)
INSERT [dbo].[Khach_Hang] ([id_khach_hang], [ho_ten], [so_dien_thoai], [email], [cmnd], [dia_chi], [gioi_tinh], [ngay_sinh], [nghe_nghiep], [id_tai_khoan]) VALUES (9, N'Nguyễn Văn Tèo', N'0101010101', N'danh@gmail.com', N'1212121212', N'Vũng Tàu 2', N'Nam', CAST(N'2004-05-15' AS Date), N'Học Sinh', 7)
INSERT [dbo].[Khach_Hang] ([id_khach_hang], [ho_ten], [so_dien_thoai], [email], [cmnd], [dia_chi], [gioi_tinh], [ngay_sinh], [nghe_nghiep], [id_tai_khoan]) VALUES (10, N'Nguyễn Nhật Minh', N'789456123', N'phong@gmail.com.vn', N'12345678', N'45', NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Khach_Hang] OFF
GO
SET IDENTITY_INSERT [dbo].[Loai_Xe] ON 

INSERT [dbo].[Loai_Xe] ([id_loai], [ten_loai], [so_cho], [tien_ve_moi_cho]) VALUES (1, N'Limousine', 24, 250000.0000)
INSERT [dbo].[Loai_Xe] ([id_loai], [ten_loai], [so_cho], [tien_ve_moi_cho]) VALUES (2, N'Ghế Ngồi', 36, 140000.0000)
SET IDENTITY_INSERT [dbo].[Loai_Xe] OFF
GO
SET IDENTITY_INSERT [dbo].[Nhan_Vien] ON 

INSERT [dbo].[Nhan_Vien] ([id_nhan_vien], [so_dien_thoai], [id_tai_khoan], [ho_ten], [email], [cmnd], [gioi_tinh], [ngay_sinh], [ngay_bd_lam_viec], [da_nghi_viec]) VALUES (1, N'0123456789', 5, N'Nguyễn Anh Tú', N'tu@gmail.com', N'191919191', N'Nữ', CAST(N'2003-05-20' AS Date), NULL, 0)
SET IDENTITY_INSERT [dbo].[Nhan_Vien] OFF
GO
SET IDENTITY_INSERT [dbo].[Tuyen_Xe] ON 

INSERT [dbo].[Tuyen_Xe] ([id_tuyen], [dia_diem_di], [dia_diem_den], [dia_diem_len_xe], [dia_diem_xuong_xe], [thoi_gian_ton], [so_km]) VALUES (1, 1, 2, N'Bến Xe Miền Đông', N'Bến Xe Đà Lạt', 8, 239)
INSERT [dbo].[Tuyen_Xe] ([id_tuyen], [dia_diem_di], [dia_diem_den], [dia_diem_len_xe], [dia_diem_xuong_xe], [thoi_gian_ton], [so_km]) VALUES (2, 2, 1, N'Bến Xe Đà Lạt', N'Bến Xe Miền Đông', 8, 239)
INSERT [dbo].[Tuyen_Xe] ([id_tuyen], [dia_diem_di], [dia_diem_den], [dia_diem_len_xe], [dia_diem_xuong_xe], [thoi_gian_ton], [so_km]) VALUES (3, 1, 3, N'Bến Xe Miền Đông', N'Bến Xe Đà Nẵng', 10, 270)
INSERT [dbo].[Tuyen_Xe] ([id_tuyen], [dia_diem_di], [dia_diem_den], [dia_diem_len_xe], [dia_diem_xuong_xe], [thoi_gian_ton], [so_km]) VALUES (4, 3, 1, N'Bến Xe Đà Nẵng', N'Bến Xe Miền Đông', 10, 270)
INSERT [dbo].[Tuyen_Xe] ([id_tuyen], [dia_diem_di], [dia_diem_den], [dia_diem_len_xe], [dia_diem_xuong_xe], [thoi_gian_ton], [so_km]) VALUES (5, 2, 3, N'Bến Xe Đà Lạt', N'Bến Xe Đà Nẵng', 6, 200)
INSERT [dbo].[Tuyen_Xe] ([id_tuyen], [dia_diem_di], [dia_diem_den], [dia_diem_len_xe], [dia_diem_xuong_xe], [thoi_gian_ton], [so_km]) VALUES (6, 3, 2, N'Bến Xe Đà Nẵng', N'Bến Xe Đà Lạt', 6, 200)
SET IDENTITY_INSERT [dbo].[Tuyen_Xe] OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([user_id], [username], [password], [enabled], [id_role]) VALUES (1, N'01214811211', N'{noop}phuong1', 1, 3)
INSERT [dbo].[users] ([user_id], [username], [password], [enabled], [id_role]) VALUES (3, N'0903414289', N'{noop}123', 1, 5)
INSERT [dbo].[users] ([user_id], [username], [password], [enabled], [id_role]) VALUES (4, N'0903414287', N'{noop}nhi', 1, 6)
INSERT [dbo].[users] ([user_id], [username], [password], [enabled], [id_role]) VALUES (5, N'0123456789', N'{noop}tu', 1, 8)
INSERT [dbo].[users] ([user_id], [username], [password], [enabled], [id_role]) VALUES (7, N'0101010101', N'{noop}123', 1, 9)
SET IDENTITY_INSERT [dbo].[users] OFF
GO
SET IDENTITY_INSERT [dbo].[Ve_Xe] ON 

INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (46, 4, 1, 4, CAST(N'2022-03-07T18:51:00' AS SmallDateTime), N'Đã thanh toán', 250000.0000, N'Ví MoMo')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (47, 4, 1, 4, CAST(N'2022-03-07T18:53:00' AS SmallDateTime), N'Đã thanh toán', 250000.0000, N'Tiền Mặt')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (48, 4, 1, 5, CAST(N'2022-03-07T18:53:00' AS SmallDateTime), N'Đã thanh toán', 250000.0000, N'Tiền Mặt')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (49, 9, 1, 3, CAST(N'2022-03-07T19:16:00' AS SmallDateTime), N'Đã thanh toán', 280000.0000, N'VNPay')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (50, 9, NULL, 5, CAST(N'2022-03-07T19:16:00' AS SmallDateTime), N'Chờ thanh toán', 250000.0000, N'VNPay')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (51, 10, NULL, 3, CAST(N'2022-03-14T19:40:00' AS SmallDateTime), N'Chờ thanh toán', 140000.0000, N'Quốc Tế')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (52, 10, NULL, 5, CAST(N'2022-03-14T19:40:00' AS SmallDateTime), N'Chờ thanh toán', 250000.0000, N'Quốc Tế')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (53, 4, NULL, 5, CAST(N'2022-03-14T20:04:00' AS SmallDateTime), N'Đã hủy', 250000.0000, N'Tiền Mặt')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (54, 7, 1, 5, CAST(N'2022-03-14T21:34:00' AS SmallDateTime), N'Đã hủy', 750000.0000, N'VNPay')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (55, 7, 1, 4, CAST(N'2022-03-31T17:05:00' AS SmallDateTime), N'Đã thanh toán', 500000.0000, N'Ví MoMo')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (56, 7, NULL, 3, CAST(N'2022-03-31T17:05:00' AS SmallDateTime), N'Đã hủy', 140000.0000, N'Ví MoMo')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (58, 10, NULL, 5, CAST(N'2022-03-31T22:40:00' AS SmallDateTime), N'Chờ thanh toán', 250000.0000, N'Tiền Mặt')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (59, 10, NULL, 5, CAST(N'2022-04-01T00:40:00' AS SmallDateTime), N'Chờ thanh toán', 250000.0000, N'Tiền Mặt')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (1055, 7, 1, 7, CAST(N'2022-04-05T23:55:00' AS SmallDateTime), N'Đã thanh toán', 280000.0000, N'Ví MoMo')
INSERT [dbo].[Ve_Xe] ([id_ve], [id_khach_hang], [id_nhan_vien_thanh_toan], [id_chuyen_xe], [ngay_lap], [trang_thai], [tong_tien], [hinh_thuc_thanh_toan]) VALUES (1056, 7, NULL, 8, CAST(N'2022-04-05T23:55:00' AS SmallDateTime), N'Chờ thanh toán', 500000.0000, N'Ví MoMo')
SET IDENTITY_INSERT [dbo].[Ve_Xe] OFF
GO
SET IDENTITY_INSERT [dbo].[Xe] ON 

INSERT [dbo].[Xe] ([id_xe], [bien_so_xe], [ma_loai_xe]) VALUES (1, N'Sài Gòn 1', 1)
INSERT [dbo].[Xe] ([id_xe], [bien_so_xe], [ma_loai_xe]) VALUES (2, N'Sài Gòn 2', 2)
INSERT [dbo].[Xe] ([id_xe], [bien_so_xe], [ma_loai_xe]) VALUES (3, N'Đà Lạt', 1)
INSERT [dbo].[Xe] ([id_xe], [bien_so_xe], [ma_loai_xe]) VALUES (5, N'AB12', 1)
INSERT [dbo].[Xe] ([id_xe], [bien_so_xe], [ma_loai_xe]) VALUES (8, N'Đà Nẵng 03', 2)
SET IDENTITY_INSERT [dbo].[Xe] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_authorities]    Script Date: 5/3/2022 5:12:11 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_authorities] ON [dbo].[authorities]
(
	[username] ASC,
	[authority] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UK_Chuyen_Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
ALTER TABLE [dbo].[Chuyen_Xe] ADD  CONSTRAINT [UK_Chuyen_Xe] UNIQUE NONCLUSTERED 
(
	[ma_xe] ASC,
	[gio_chay] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_Ten_Dia_Diem]    Script Date: 5/3/2022 5:12:11 PM ******/
ALTER TABLE [dbo].[Dia_Diem] ADD  CONSTRAINT [UK_Ten_Dia_Diem] UNIQUE NONCLUSTERED 
(
	[ten_dia_diem] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_Khach_Hang]    Script Date: 5/3/2022 5:12:11 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_Khach_Hang] ON [dbo].[Khach_Hang]
(
	[so_dien_thoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_Nhan_Vien]    Script Date: 5/3/2022 5:12:11 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_Nhan_Vien] ON [dbo].[Nhan_Vien]
(
	[so_dien_thoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [IX_users]    Script Date: 5/3/2022 5:12:11 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_users] ON [dbo].[users]
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_Xe]    Script Date: 5/3/2022 5:12:11 PM ******/
ALTER TABLE [dbo].[Xe] ADD  CONSTRAINT [UK_Xe] UNIQUE NONCLUSTERED 
(
	[bien_so_xe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[users] ADD  CONSTRAINT [DF_users_enabled]  DEFAULT ((1)) FOR [enabled]
GO
ALTER TABLE [dbo].[authorities]  WITH CHECK ADD  CONSTRAINT [FK_authorities_users] FOREIGN KEY([username])
REFERENCES [dbo].[users] ([username])
GO
ALTER TABLE [dbo].[authorities] CHECK CONSTRAINT [FK_authorities_users]
GO
ALTER TABLE [dbo].[Chuyen_Xe]  WITH CHECK ADD  CONSTRAINT [FK_Chuyen_Xe_Tuyen_Xe] FOREIGN KEY([ma_tuyen])
REFERENCES [dbo].[Tuyen_Xe] ([id_tuyen])
GO
ALTER TABLE [dbo].[Chuyen_Xe] CHECK CONSTRAINT [FK_Chuyen_Xe_Tuyen_Xe]
GO
ALTER TABLE [dbo].[Chuyen_Xe]  WITH CHECK ADD  CONSTRAINT [FK_Chuyen_Xe_Xe] FOREIGN KEY([ma_xe])
REFERENCES [dbo].[Xe] ([id_xe])
GO
ALTER TABLE [dbo].[Chuyen_Xe] CHECK CONSTRAINT [FK_Chuyen_Xe_Xe]
GO
ALTER TABLE [dbo].[CT_Ve_Ghe]  WITH CHECK ADD  CONSTRAINT [FK_CTVeGhe_Ghe] FOREIGN KEY([id_ghe])
REFERENCES [dbo].[Ghe] ([ma_ghe])
GO
ALTER TABLE [dbo].[CT_Ve_Ghe] CHECK CONSTRAINT [FK_CTVeGhe_Ghe]
GO
ALTER TABLE [dbo].[CT_Ve_Ghe]  WITH CHECK ADD  CONSTRAINT [FK_CTVeGhe_VeXe] FOREIGN KEY([id_ve])
REFERENCES [dbo].[Ve_Xe] ([id_ve])
GO
ALTER TABLE [dbo].[CT_Ve_Ghe] CHECK CONSTRAINT [FK_CTVeGhe_VeXe]
GO
ALTER TABLE [dbo].[CT_Xe_Ghe]  WITH CHECK ADD  CONSTRAINT [FK_CTXeGhe_Ghe] FOREIGN KEY([ma_ghe])
REFERENCES [dbo].[Ghe] ([ma_ghe])
GO
ALTER TABLE [dbo].[CT_Xe_Ghe] CHECK CONSTRAINT [FK_CTXeGhe_Ghe]
GO
ALTER TABLE [dbo].[CT_Xe_Ghe]  WITH CHECK ADD  CONSTRAINT [FK_IdXeCTXeGhe_IdXe] FOREIGN KEY([id_xe])
REFERENCES [dbo].[Xe] ([id_xe])
GO
ALTER TABLE [dbo].[CT_Xe_Ghe] CHECK CONSTRAINT [FK_IdXeCTXeGhe_IdXe]
GO
ALTER TABLE [dbo].[Khach_Hang]  WITH CHECK ADD  CONSTRAINT [FK_Khach_Hang_users1] FOREIGN KEY([id_tai_khoan])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[Khach_Hang] CHECK CONSTRAINT [FK_Khach_Hang_users1]
GO
ALTER TABLE [dbo].[Nhan_Vien]  WITH CHECK ADD  CONSTRAINT [FK_Nhan_Vien_users] FOREIGN KEY([id_tai_khoan])
REFERENCES [dbo].[users] ([user_id])
GO
ALTER TABLE [dbo].[Nhan_Vien] CHECK CONSTRAINT [FK_Nhan_Vien_users]
GO
ALTER TABLE [dbo].[Tuyen_Xe]  WITH CHECK ADD  CONSTRAINT [FK_TuyenXe_DiaDiemDen] FOREIGN KEY([dia_diem_den])
REFERENCES [dbo].[Dia_Diem] ([id_dia_diem])
GO
ALTER TABLE [dbo].[Tuyen_Xe] CHECK CONSTRAINT [FK_TuyenXe_DiaDiemDen]
GO
ALTER TABLE [dbo].[Tuyen_Xe]  WITH CHECK ADD  CONSTRAINT [FK_TuyenXe_DiaDiemDi] FOREIGN KEY([dia_diem_di])
REFERENCES [dbo].[Dia_Diem] ([id_dia_diem])
GO
ALTER TABLE [dbo].[Tuyen_Xe] CHECK CONSTRAINT [FK_TuyenXe_DiaDiemDi]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_users_authorities] FOREIGN KEY([id_role])
REFERENCES [dbo].[authorities] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK_users_authorities]
GO
ALTER TABLE [dbo].[Ve_Xe]  WITH CHECK ADD  CONSTRAINT [FK_Ve_Xe_Chuyen_Xe] FOREIGN KEY([id_chuyen_xe])
REFERENCES [dbo].[Chuyen_Xe] ([ma_chuyen])
GO
ALTER TABLE [dbo].[Ve_Xe] CHECK CONSTRAINT [FK_Ve_Xe_Chuyen_Xe]
GO
ALTER TABLE [dbo].[Ve_Xe]  WITH CHECK ADD  CONSTRAINT [FK_VeXe_KhachHang] FOREIGN KEY([id_khach_hang])
REFERENCES [dbo].[Khach_Hang] ([id_khach_hang])
GO
ALTER TABLE [dbo].[Ve_Xe] CHECK CONSTRAINT [FK_VeXe_KhachHang]
GO
ALTER TABLE [dbo].[Ve_Xe]  WITH CHECK ADD  CONSTRAINT [FK_VeXe_NhanVien] FOREIGN KEY([id_nhan_vien_thanh_toan])
REFERENCES [dbo].[Nhan_Vien] ([id_nhan_vien])
GO
ALTER TABLE [dbo].[Ve_Xe] CHECK CONSTRAINT [FK_VeXe_NhanVien]
GO
ALTER TABLE [dbo].[Xe]  WITH CHECK ADD  CONSTRAINT [FK_Xe_Loai_Xe] FOREIGN KEY([ma_loai_xe])
REFERENCES [dbo].[Loai_Xe] ([id_loai])
GO
ALTER TABLE [dbo].[Xe] CHECK CONSTRAINT [FK_Xe_Loai_Xe]
GO
/****** Object:  StoredProcedure [dbo].[GET_ALL_VE_XE_CHUA_THANH_TOAN]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GET_ALL_VE_XE_CHUA_THANH_TOAN]
AS
	SELECT * FROM Ve_Xe VX WHERE VX.id_nhan_vien_thanh_toan IS NULL 
GO
/****** Object:  StoredProcedure [dbo].[GET_VE_XE_LIST]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[GET_VE_XE_LIST] @userId INT
AS
	SELECT * FROM Ve_Xe WHERE Ve_Xe.id_khach_hang = @userId

	
GO
/****** Object:  StoredProcedure [dbo].[Lay_Chuyen_Xe_Trong_Ngay]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Lay_Chuyen_Xe_Trong_Ngay]
	@Ngay DATE, @idTuyenXe INT
AS
	SELECT * FROM Chuyen_Xe CX WHERE CAST(CX.gio_chay AS DATE) = @Ngay AND CX.ma_tuyen = @idTuyenXe
	ORDER BY CX.gio_chay 

GO
/****** Object:  StoredProcedure [dbo].[TIM_KIEM_VE]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TIM_KIEM_VE]
(@idKhachHang INT ,@maVe INT = NULL,@dateInput DATE = NULL
, @diaDiemDi INT = NULL, @diaDiemDen INT = NULL, @trangThaiInput NVARCHAR(30) = NULL)
AS
BEGIN
	IF(@maVe IS NOT NULL)
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.id_ve = @maVe AND VX.id_khach_hang = @idKhachHang
		END
	ELSE IF(@dateInput IS NOT NULL AND @diaDiemDi IS NULL AND @diaDiemDen IS NULL AND @trangThaiInput IS NULL )
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE CAST(VX.ngay_lap AS DATE) = @dateInput AND VX.id_khach_hang = @idKhachHang
		END
	ELSE IF (@dateInput IS NOT NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NULL)
		BEGIN
			SELECT * INTO #veXeWithDateInput FROM Ve_Xe VX WHERE CAST(VX.ngay_lap AS DATE) = @dateInput AND VX.id_khach_hang = @idKhachHang
			DECLARE @id_tuyen_co_diem_di_den INT;
			SET @id_tuyen_co_diem_di_den = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den

			SELECT * FROM #veXeWithDateInput TEMP1,#chuyenXeThuocTuyenXe TEMP2 WHERE TEMP1.id_chuyen_xe = TEMP2.ma_chuyen 
			
		END

	ELSE IF (@dateInput IS NOT NULL AND @trangThaiInput IS NOT NULL AND @diaDiemDi IS NULL AND @diaDiemDen IS NULL)
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.id_khach_hang = @idKhachHang AND CAST(VX.ngay_lap AS DATE) = @dateInput AND VX.trang_thai = @trangThaiInput
		END

	ELSE IF(@dateInput IS NOT NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NOT NULL )
		BEGIN
			
			SELECT * INTO #veXeWithDateInput1 FROM Ve_Xe VX WHERE VX.id_khach_hang = @idKhachHang AND CAST(VX.ngay_lap AS DATE) = @dateInput AND VX.trang_thai = @trangThaiInput 
			DECLARE @id_tuyen_co_diem_di_den1 INT;
			SET @id_tuyen_co_diem_di_den1 = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe1 FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den1

			SELECT * FROM #veXeWithDateInput1 TEMP1,#chuyenXeThuocTuyenXe1 TEMP2 WHERE TEMP1.id_chuyen_xe = TEMP2.ma_chuyen 

	
		END
	ELSE IF (@dateInput IS NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NULL)
		BEGIN
			DECLARE @id_tuyen_co_diem_di_den2 INT;
			SET @id_tuyen_co_diem_di_den2 = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe2 FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den2

			SELECT * FROM Ve_Xe VX, #chuyenXeThuocTuyenXe2 TEMP WHERE VX.id_chuyen_xe = TEMP.ma_chuyen AND VX.id_khach_hang = @idKhachHang
		END
	ELSE IF (@dateInput IS NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NOT NULL)
		BEGIN
			-- select những vé có trạng thái 
			SELECT * INTO #veXeCoTrangThai FROM Ve_Xe VX WHERE VX.trang_thai = @trangThaiInput AND VX.id_khach_hang = @idKhachHang
			DECLARE @id_tuyen_co_diem_di_den3 INT;
			SET @id_tuyen_co_diem_di_den3 = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe3 FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den3

			SELECT * FROM #veXeCoTrangThai VX, #chuyenXeThuocTuyenXe3 TEMP WHERE VX.id_chuyen_xe = TEMP.ma_chuyen
		END
	ELSE IF (@dateInput IS NULL AND @diaDiemDi IS NULL AND @diaDiemDen IS NULL AND @trangThaiInput IS NOT NULL)	
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.trang_thai = @trangThaiInput AND VX.id_khach_hang = @idKhachHang
		END
	ELSE 
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.id_khach_hang = @idKhachHang
		END
END
GO
/****** Object:  StoredProcedure [dbo].[TIM_KIEM_VE_NV]    Script Date: 5/3/2022 5:12:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[TIM_KIEM_VE_NV]
(@maVe INT = NULL,@dateInput DATE = NULL
, @diaDiemDi INT = NULL, @diaDiemDen INT = NULL, @trangThaiInput NVARCHAR(30) = NULL)
AS
BEGIN
	IF(@maVe IS NOT NULL)
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.id_ve = @maVe 
		END
	ELSE IF(@dateInput IS NOT NULL AND @diaDiemDi IS NULL AND @diaDiemDen IS NULL AND @trangThaiInput IS NULL )
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE CAST(VX.ngay_lap AS DATE) = @dateInput 
		END
	ELSE IF (@dateInput IS NOT NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NULL)
		BEGIN
			SELECT * INTO #veXeWithDateInput FROM Ve_Xe VX WHERE CAST(VX.ngay_lap AS DATE) = @dateInput 
			DECLARE @id_tuyen_co_diem_di_den INT;
			SET @id_tuyen_co_diem_di_den = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den

			SELECT * FROM #veXeWithDateInput TEMP1,#chuyenXeThuocTuyenXe TEMP2 WHERE TEMP1.id_chuyen_xe = TEMP2.ma_chuyen 
			
		END

	ELSE IF (@dateInput IS NOT NULL AND @trangThaiInput IS NOT NULL AND @diaDiemDi IS NULL AND @diaDiemDen IS NULL)
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE  CAST(VX.ngay_lap AS DATE) = @dateInput AND VX.trang_thai = @trangThaiInput
		END

	ELSE IF(@dateInput IS NOT NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NOT NULL )
		BEGIN
			
			SELECT * INTO #veXeWithDateInput1 FROM Ve_Xe VX WHERE CAST(VX.ngay_lap AS DATE) = @dateInput AND VX.trang_thai = @trangThaiInput 
			DECLARE @id_tuyen_co_diem_di_den1 INT;
			SET @id_tuyen_co_diem_di_den1 = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe1 FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den1

			SELECT * FROM #veXeWithDateInput1 TEMP1,#chuyenXeThuocTuyenXe1 TEMP2 WHERE TEMP1.id_chuyen_xe = TEMP2.ma_chuyen 

	
		END
	ELSE IF (@dateInput IS NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NULL)
		BEGIN
			DECLARE @id_tuyen_co_diem_di_den2 INT;
			SET @id_tuyen_co_diem_di_den2 = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe2 FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den2

			SELECT * FROM Ve_Xe VX, #chuyenXeThuocTuyenXe2 TEMP WHERE VX.id_chuyen_xe = TEMP.ma_chuyen 
		END
	ELSE IF (@dateInput IS NULL AND @diaDiemDi IS NOT NULL AND @diaDiemDen IS NOT NULL AND @trangThaiInput IS NOT NULL)
		BEGIN
			-- select những vé có trạng thái 
			SELECT * INTO #veXeCoTrangThai FROM Ve_Xe VX WHERE VX.trang_thai = @trangThaiInput 
			DECLARE @id_tuyen_co_diem_di_den3 INT;
			SET @id_tuyen_co_diem_di_den3 = (SELECT TX.id_tuyen FROM Tuyen_Xe TX WHERE TX.dia_diem_di = @diaDiemDi AND TX.dia_diem_den = @diaDiemDen)
			
			-- SELECT những thằng chuyến xe
			SELECT * INTO #chuyenXeThuocTuyenXe3 FROM Chuyen_Xe CX WHERE CX.ma_tuyen = @id_tuyen_co_diem_di_den3

			SELECT * FROM #veXeCoTrangThai VX, #chuyenXeThuocTuyenXe3 TEMP WHERE VX.id_chuyen_xe = TEMP.ma_chuyen
		END
	ELSE IF (@dateInput IS NULL AND @diaDiemDi IS NULL AND @diaDiemDen IS NULL AND @trangThaiInput IS NOT NULL)	
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.trang_thai = @trangThaiInput 
		END
	ELSE 
		BEGIN
			SELECT * FROM Ve_Xe VX WHERE VX.trang_thai = N'Chờ thanh toán'
		END
END
GO
USE [master]
GO
ALTER DATABASE [BenXe] SET  READ_WRITE 
GO
